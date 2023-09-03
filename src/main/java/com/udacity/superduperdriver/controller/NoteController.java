package com.udacity.superduperdriver.controller;

import com.udacity.superduperdriver.model.Notes;
import com.udacity.superduperdriver.service.NoteService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/note")
public class NoteController {

    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/save")
    public String addNotes(Model model, @ModelAttribute("notes") Notes notes) {
        noteService.save(notes);
        model.addAttribute("success", "Save Note successfully");
        return "result";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteNotes(@PathVariable Long id, Model model) {
        try {
            noteService.deleteById(id);
            model.addAttribute("success", "Delete note successfully");
            return "result";
        }catch (Exception exception){
            model.addAttribute("error", "Delete Note Error");
            return "result";
        }
    }

    @PostMapping("/update/{id}")
    public String updateNotes(Model model, @ModelAttribute("notes") Notes notes){
        noteService.update(notes);
        model.addAttribute("success", "Update Note successfully");
        return "result";
    }
}
