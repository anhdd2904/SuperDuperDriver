package com.udacity.superduperdriver.security;

import com.udacity.superduperdriver.mapper.UserMapper;
import com.udacity.superduperdriver.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuInfoUser {
    private UserMapper userMapper;

    @Autowired
    public AuInfoUser(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Users getInfoUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userMapper.getUserByUsername(authentication.getName());
    }
}
