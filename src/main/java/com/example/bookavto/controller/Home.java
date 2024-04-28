package com.example.bookavto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home {
    @GetMapping("/")
    public String homePage(){
        return "index";
    }
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
