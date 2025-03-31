package com.taewoo.crudapplication.controller;

import com.taewoo.crudapplication.entity.User;
import com.taewoo.crudapplication.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password, HttpServletRequest request, Model model) {
        try {
            User user = userService.register(username, password);
            userService.autoLogin(user, request);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
        return "redirect:/boards/list";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
