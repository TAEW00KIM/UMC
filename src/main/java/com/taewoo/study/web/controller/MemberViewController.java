package com.taewoo.study.web.controller;

import com.taewoo.study.service.memberService.MemberCommandService;
import com.taewoo.study.web.dto.memberDto.MemberRequestDTO;
import lombok.extern.flogger.Flogger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberViewController {
    private final MemberCommandService memberCommandService;

    public MemberViewController(MemberCommandService memberCommandService) {
        this.memberCommandService = memberCommandService;
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("memberJoinDTO", new MemberRequestDTO.JoinDTO());
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("memberJoinDTO", new MemberRequestDTO.JoinDTO());
        return "signup";
    }

    @PostMapping("/members/signup")
    public String joinMember(
            @ModelAttribute("memberJoinDTO") MemberRequestDTO.JoinDTO request,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        try {
            memberCommandService.joinMember(request);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
