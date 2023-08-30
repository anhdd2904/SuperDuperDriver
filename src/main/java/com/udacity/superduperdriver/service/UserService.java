package com.udacity.superduperdriver.service;

import com.udacity.superduperdriver.model.Users;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;

public interface UserService {
    int save(Users users) throws DuplicateMemberException;
}
