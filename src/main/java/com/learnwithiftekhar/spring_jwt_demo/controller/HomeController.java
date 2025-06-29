package com.learnwithiftekhar.spring_jwt_demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HomeController {

    @GetMapping("/hello")
    public String hello(Authentication auth) {
        return "Hello "+auth.getName();
    }
}
