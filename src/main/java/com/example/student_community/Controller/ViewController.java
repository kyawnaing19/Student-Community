package com.example.student_community.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/friends")
    public String friends() {
        return "friends";
    }

    @GetMapping("/allFriends")
    public String allFriends() {
        return "allFriends";
    }

    @GetMapping("/search")
    public String search(@RequestParam String query, Model model) {
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/suggestions")
    public String suggestions() {
        return "suggestions";
    }
}


