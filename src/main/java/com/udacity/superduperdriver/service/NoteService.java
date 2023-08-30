package com.udacity.superduperdriver.service;

import com.udacity.superduperdriver.model.Notes;

import java.util.List;

public interface NoteService {

    int save(Notes notes);

    int deleteById (Long id);

    int update (Notes notes);

    List<Notes> findByUser ();

}
