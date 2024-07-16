package com.example.student_community.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/reg")
    public String reg() {
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }
}


