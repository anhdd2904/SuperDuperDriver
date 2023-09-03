package com.udacity.superduperdriver.service;

import com.udacity.superduperdriver.dto.CredentialsDTO;
import com.udacity.superduperdriver.model.Credentials;
import org.apache.ibatis.javassist.NotFoundException;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface CredentialsService {

    int save(Credentials credentials) throws DuplicateMemberException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException;
    int delete(Long id) throws NotFoundException;
    int update(Credentials credentials) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException;

    List<CredentialsDTO> findAllByUser();

    CredentialsDTO findById(Long id);

}
