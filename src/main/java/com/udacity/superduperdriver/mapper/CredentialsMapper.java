package com.udacity.superduperdriver.mapper;

import com.udacity.superduperdriver.dto.CredentialsDTO;
import com.udacity.superduperdriver.model.Credentials;
import org.apache.ibatis.annotations.*;

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

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    int delete(Long id);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, password = #{password},credentialkey = #{key}  WHERE credentialid = #{credentialId}")
    int update(Credentials credentials);

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    List<CredentialsDTO> findAllByUser(Long idUser);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    Credentials findById(Long id);


}
