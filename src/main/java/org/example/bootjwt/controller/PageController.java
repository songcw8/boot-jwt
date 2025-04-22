package org.example.bootjwt.controller;

import org.example.bootjwt.auth.JwtProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    private final JwtProvider jwtProvider;

    public PageController(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @GetMapping
    public String index(Model model, Authentication auth) {
        String token = jwtProvider.createToken(auth);
        model.addAttribute("jwt", token);
        return "index";
    }
}
