package com.example.student_community.Controller;

import com.example.student_community.Model.User;
import com.example.student_community.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private UserService userService;
    @PostMapping("/createUser")
    public ResponseEntity createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}
