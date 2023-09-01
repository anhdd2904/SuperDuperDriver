package com.udacity.superduperdriver.mapper;

import com.udacity.superduperdriver.model.Credentials;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CredentialsMapper {
    @Insert("INSERT INTO CREDENTIALS (url, username, credentialkey, password, userid) values (#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int save(Credentials credentials);

    @Select("SELECT COUNT(*) \n" +
            "FROM CREDENTIALS \n" +
            "WHERE username = #{username} and url = #{url}")
    int existsByUsernameAndUrl(String username, String url);


    int delete(Long id);
    int update(Credentials credentials);
    List<Credentials> findAllByUser(Long idUser);
    Credentials findById(Long id);


}
