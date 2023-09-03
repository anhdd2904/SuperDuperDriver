package com.udacity.superduperdriver.controller;

import com.udacity.superduperdriver.security.AuInfoUser;
import com.udacity.superduperdriver.service.CredentialsService;
import com.udacity.superduperdriver.service.FileService;
import com.udacity.superduperdriver.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    private FileService fileService;
    private NoteService noteService;
    private CredentialsService credentialsService;
    private AuInfoUser auInfoUser;

    @Autowired
    public HomeController(FileService fileService, NoteService noteService, AuInfoUser auInfoUser, CredentialsService credentialsService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialsService = credentialsService;
    }

    @GetMapping("/home")
    public String getFilesByIdUser(Model model) {
        try {
            model.addAttribute("files", fileService.findByIdUser());
            model.addAttribute("notes", noteService.findByUser());
            model.addAttribute("credentialsList" , credentialsService.findAllByUser());
            return "home";
        } catch (NullPointerException e) {
            return "login";
        }
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }


}
