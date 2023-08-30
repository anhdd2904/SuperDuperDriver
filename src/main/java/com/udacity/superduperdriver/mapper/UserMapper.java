package com.udacity.superduperdriver.mapper;

import com.udacity.superduperdriver.model.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) values (#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userid")
    int save(Users users);

    @Select("SELECT COUNT(*) \n" +
            "FROM USERS \n" +
            "WHERE username = #{username} ")
    int existsByUsername(String username);

    @Select("SELECT * \n" +
            "FROM USERS \n" +
            "WHERE username = #{username} ")
    Users getUserByUsername(String username);
}
