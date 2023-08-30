package com.udacity.superduperdriver.service;

import com.udacity.superduperdriver.model.Files;

import java.util.List;

public interface FileService {
    int save(Files files);

    int deleteByIdFile(Long id);

    List<Files> findByIdUser();

    Files findByIdFile(Long id);

    List<Files> findAllFiles();


}
