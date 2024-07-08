package com.example.student_community.Controller;

import com.example.student_community.MailVerification.RegisterService;
import com.example.student_community.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class UserController {

    @Autowired
    private RegisterService registerService;

    @GetMapping
    public String reg(Model model) {
        model.addAttribute("user", new User());
        return "index"; // Your HTML form page
    }

    @PostMapping
    public ResponseEntity<String> processRegister(@RequestBody User user) {
        if (registerService.isUserRegistered(user)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User is already registered");
        }
        registerService.registerUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("Registration successful");
    }
}






    //


//    @RestController
//    @RequestMapping("/reg")
//    public class RegistrationController {
//
//        @Autowired
//        private RegisterService registerService;
//
//        @GetMapping
//        public String reg(Model model) {
//            model.addAttribute("user", new User());
//            return "index"; // Your HTML form page
//        }
//
//        @PostMapping
//        public ResponseEntity<String> processRegister(@RequestBody User user) {
//            if (registerService.isUserRegistered(user)) {
//                return ResponseEntity.status(HttpStatus.CONFLICT).body("User is already registered");
//            }
//            registerService.registerUser(user);
//            return ResponseEntity.status(HttpStatus.OK).body("Registration successful");
//        }
//    }
//


    //

