package com.udacity.superduperdriver.controller;

import com.udacity.superduperdriver.model.Files;
import com.udacity.superduperdriver.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping(value = "/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public String uploadFiles(@RequestParam("fileUpload") MultipartFile file, Model model) throws IOException {
        if (!file.isEmpty()) {
            Files files = new Files();
            files.setFileName(file.getOriginalFilename());
            files.setContentType(file.getContentType());
            files.setFileSize(String.valueOf(file.getSize()));
            files.setFileData(file.getBytes());
            fileService.save(files);
            model.addAttribute("success", "Upload File successfully");
        }
        return "result";
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable Long id){
        Files files = fileService.findByIdFile(id);
        if(files.equals(null)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(files.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + files.getFileName() + "\"")
                .body(new ByteArrayResource(files.getFileData()));
    }

    @DeleteMapping("/delete/{id}")
    public String deleteFileById(@PathVariable Long id,Model model) {
        try {
            fileService.deleteByIdFile(id);
            model.addAttribute("success", "Delete file successfully");
            return "result";
        }catch (Exception exception){
            model.addAttribute("error", "Delete file Error");
            return "result";
        }
    }
}
