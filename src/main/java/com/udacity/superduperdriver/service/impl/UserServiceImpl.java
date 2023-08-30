package com.udacity.superduperdriver.service.impl;

import com.udacity.superduperdriver.mapper.UserMapper;
import com.udacity.superduperdriver.model.Users;
import com.udacity.superduperdriver.security.HashService;
import com.udacity.superduperdriver.service.UserService;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;
    private HashService hashService;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    @Override
    public int save(Users users) throws DuplicateMemberException {
        if (userMapper.existsByUsername(users.getUsername()) > 0){
            throw new DuplicateMemberException("This username already exists");
        }
        byte[] salt = new byte[20];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        users.setSalt(encodedSalt);
        users.setPassword(hashService.getHashedValue(users.getPassword(),encodedSalt));
        return userMapper.save(users);
    }
}
