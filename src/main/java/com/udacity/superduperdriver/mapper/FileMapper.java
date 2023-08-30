package com.udacity.superduperdriver.mapper;

import com.udacity.superduperdriver.model.Files;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Insert("INSERT INTO FILES (userid, filename, contenttype, filesize, filedata) values (#{userId}, #{fileName}, #{contentType}, #{fileSize}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int save (Files files);

    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    List<Files> findByIdUser(Long id);

    @Delete("DELETE FROM FILES WHERE fileid  = #{fileId} ")
    int deleteFilesById(Long id);

    @Select("SELECT * FROM FILES WHERE fileid = #{fileId}")
    Files findByIdFile(Long id);
}
