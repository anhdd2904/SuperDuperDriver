package com.udacity.superduperdriver.mapper;

import com.udacity.superduperdriver.model.Notes;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) values (#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int save(Notes notes);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    int deleteById(Long id);

    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    List<Notes> findByIdUser(Long userId);

    @Update("UPDATE NOTES SET notetitle = #{noteTitle}, notedescription = #{noteDescription}, userid = #{userId} WHERE noteid = #{noteId}")
    int update (Notes notes);

    @Select("SELECT * FROM NOTES WHERE noteid = #{noteId")
    Notes selectById (Long id);
}
