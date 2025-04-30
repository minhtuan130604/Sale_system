package com.example.Sale_system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class MVCController {

    private static final Logger LOG = LoggerFactory.getLogger(MVCController.class);

    @GetMapping("/login-page") // Đổi thành /auth/login-page thay vì /auth/login
    public String login() {
        LOG.info("Trả về login.html");
        return "login";
    }
}

