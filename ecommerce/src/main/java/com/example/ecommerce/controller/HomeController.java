package com.example.ecommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home/index"; // Charge templates/home/index.html
    }
}