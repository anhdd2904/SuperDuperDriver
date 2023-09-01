package com.udacity.superduperdriver.controller;

import com.udacity.superduperdriver.model.Users;
import com.udacity.superduperdriver.service.UserService;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "/signup")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String signup() {
        return "signup";
    }

    @PostMapping
    public String save(RedirectAttributes redirectAttributes, Model model, @ModelAttribute("user") Users users) throws DuplicateMemberException {
        try {
            String result1 = "You successfully signed up!";
            userService.save(users);
            model.addAttribute("result", "Successfully!");
            redirectAttributes.addFlashAttribute("result1", result1);
            return "redirect:/login";
        } catch (DuplicateMemberException exception) {
            model.addAttribute("error", "Username already exists!");
            return "signup";
        }
    }
}
