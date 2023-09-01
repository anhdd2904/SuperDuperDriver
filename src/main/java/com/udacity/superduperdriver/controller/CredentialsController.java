package com.udacity.superduperdriver.controller;

import com.udacity.superduperdriver.model.Credentials;
import com.udacity.superduperdriver.service.CredentialsService;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping("/credential")
public class CredentialsController {

    private CredentialsService credentialsService;

    @Autowired
    public CredentialsController(CredentialsService credentialsService) {
        this.credentialsService = credentialsService;
    }

    @PostMapping("/save")
    public String saveCredentials(RedirectAttributes redirectAttributes, Model model, @ModelAttribute("credentials")Credentials credentials) throws  NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        try {
            credentialsService.save(credentials);
            redirectAttributes.addFlashAttribute("result1", "You successfully signed up!");
            model.addAttribute("success", "Note successfully saved.");
            return "result";
        } catch (DuplicateMemberException exception) {
            model.addAttribute("error", "Username already exists!");
            return "signup";
        }
    }
}
