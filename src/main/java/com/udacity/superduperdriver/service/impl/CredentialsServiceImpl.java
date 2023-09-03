package com.udacity.superduperdriver.service.impl;

import com.udacity.superduperdriver.dto.CredentialsDTO;
import com.udacity.superduperdriver.mapper.CredentialsMapper;
import com.udacity.superduperdriver.model.Credentials;
import com.udacity.superduperdriver.security.AuInfoUser;
import com.udacity.superduperdriver.security.EncryptionService;
import com.udacity.superduperdriver.service.CredentialsService;
import org.apache.ibatis.javassist.NotFoundException;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialsServiceImpl implements CredentialsService {

    private CredentialsMapper credentialsMapper;
    private AuInfoUser auInfoUser;
    private EncryptionService encryptionService;

    @Autowired
    public CredentialsServiceImpl(CredentialsMapper credentialsMapper, AuInfoUser auInfoUser, EncryptionService encryptionService) {
        this.credentialsMapper = credentialsMapper;
        this.auInfoUser = auInfoUser;
        this.encryptionService = encryptionService;
    }

    @Override
    public int save(Credentials credentials) throws DuplicateMemberException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        if (credentialsMapper.existsByUsernameAndUrl(credentials.getUsername(), credentials.getUrl()) > 0) {
            throw new DuplicateMemberException("This username and url already exists");
        }
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credentials.getPassword(), encodedKey);

        Credentials credentialsDto = credentials;
        Long idUser = auInfoUser.getInfoUser().getUserid();
        credentialsDto.setUserId(idUser);
        credentialsDto.setKey(encodedKey);
        credentialsDto.setPassword(encryptedPassword);

        return credentialsMapper.save(credentialsDto);
    }

    @Override
    public int delete(Long id) throws NotFoundException {
        if(credentialsMapper.findById(id) == null) {
            throw new NotFoundException("Data not found for id CREDENTIALS : "+ id);
        }
        return credentialsMapper.delete(id);
    }

    @Override
    public int update(Credentials credentials) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        Long id = credentials.getCredentialId();
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credentials.getPassword(), encodedKey);

        credentials.setPassword(encryptedPassword);
        credentials.setKey(encodedKey);
        return credentialsMapper.update(credentials);
    }

    @Override
    public List<CredentialsDTO> findAllByUser() {
        Long idUser = auInfoUser.getInfoUser().getUserid();
        List<CredentialsDTO> credentialsDTOList1 = new ArrayList<>();

        try {
            List<CredentialsDTO> credentialsDTOList = credentialsMapper.findAllByUser(idUser);

            for (CredentialsDTO e : credentialsDTOList) {
                e.setDecryptedPassword(encryptionService.decryptValue(e.getPassword(), e.getCredentialKey()));
                credentialsDTOList1.add(e);
            }

            return credentialsDTOList1;

        } catch (NullPointerException exception) {
            System.out.println("credential not found");
            return null;
        } catch (NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException |
                 InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CredentialsDTO findById(Long id) {

        return null;
    }
}
