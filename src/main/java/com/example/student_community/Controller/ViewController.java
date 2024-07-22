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
    public String friendsForm() {
        return "friends";
    }

    @GetMapping("/search")
    public String searchForm(@RequestParam String query, Model model) {
        model.addAttribute("input",query);
           return "search";
    }

    @GetMapping("/friendRequestForm")
    public String requestForm(@RequestParam int query, Model model) {
        model.addAttribute("input",query);
        return "friendRequestForm";
    }


}


