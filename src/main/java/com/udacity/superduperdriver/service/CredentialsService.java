package com.udacity.superduperdriver.service;

import com.udacity.superduperdriver.model.Credentials;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface CredentialsService {

    int save(Credentials credentials) throws DuplicateMemberException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException;
    int delete(Long id);
    int update(Credentials credentials);

    List<Credentials> findAllByUser();

    Credentials findById(Long id);

}
