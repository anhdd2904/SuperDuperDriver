package com.udacity.superduperdriver.service.impl;

import com.udacity.superduperdriver.mapper.NoteMapper;
import com.udacity.superduperdriver.model.Notes;
import com.udacity.superduperdriver.security.AuInfoUser;
import com.udacity.superduperdriver.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private NoteMapper noteMapper;
    private AuInfoUser auInfoUser;

    @Autowired
    public NoteServiceImpl(NoteMapper noteMapper, AuInfoUser auInfoUser) {
        this.noteMapper = noteMapper;
        this.auInfoUser = auInfoUser;
    }

    @Override
    public int save(Notes notes) {
        if (notes == null) {
            throw new IllegalArgumentException("Notes can not be null");
        } else {
            Long idUser = auInfoUser.getInfoUser().getUserid();
            notes.setUserId(idUser);
        }
        return noteMapper.save(notes);
    }

    @Override
    public int deleteById(Long id) {
        return noteMapper.deleteById(id);
    }

    @Override
    public int update(Notes notes) {
        Long idUser = auInfoUser.getInfoUser().getUserid();
        notes.setUserId(idUser);
        System.out.println(notes);
        return noteMapper.update(notes);
    }

    @Override
    public List<Notes> findByUser() {
        Long idUser = auInfoUser.getInfoUser().getUserid();
        List<Notes> notesList = noteMapper.findByUser(idUser);
        return notesList;
    }
}
