package com.udacity.superduperdriver.service.impl;

import com.udacity.superduperdriver.mapper.FileMapper;
import com.udacity.superduperdriver.model.Files;
import com.udacity.superduperdriver.security.AuInfoUser;
import com.udacity.superduperdriver.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    private FileMapper fileMapper;
    private AuInfoUser auInfoUser;

    @Autowired
    public FileServiceImpl(FileMapper fileMapper, AuInfoUser auInfoUser) {
        this.fileMapper = fileMapper;
        this.auInfoUser = auInfoUser;
    }

    @Override
    public int save(Files files) {
        if (files.equals(null)) {
            throw new IllegalArgumentException("files can not be null");
        }
        Long idUser = auInfoUser.getInfoUser().getUserid();
        files.setUserId(idUser);
        return fileMapper.save(files);
    }

    @Override
    public int deleteByIdFile(Long id) {
        return fileMapper.deleteFilesById(id);
    }

    @Override
    public List<Files> findByIdUser() {
        Long idUser = auInfoUser.getInfoUser().getUserid();
        List<Files> filesList = fileMapper.findByIdUser(idUser);
        return filesList;
    }

    @Override
    public Files findByIdFile(Long id) {
        Files files = fileMapper.findByIdFile(id);
        if (files.equals(null)) {
            return null;
        }
        return files;
    }

    @Override
    public List<Files> findAllFiles() {
        return null;
    }
}
