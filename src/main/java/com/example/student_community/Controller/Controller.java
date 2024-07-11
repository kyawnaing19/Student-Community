package com.example.student_community.Controller;

import com.example.student_community.Model.User;
import com.example.student_community.Repository.UserRepository;
import com.example.student_community.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/createUser")
    public ResponseEntity createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
    @GetMapping("/test")
    public String test(){
        return "test";
    }




}
