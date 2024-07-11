package com.example.student_community.Controller;

import com.example.student_community.Model.User;
import com.example.student_community.Repository.UserRepository;
import com.example.student_community.Security.JwtResponse;
import com.example.student_community.Security.JwtUtil;
import com.example.student_community.Security.LoginRequest;
import com.example.student_community.Services.LoginService;
import com.example.student_community.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password,Model model,HttpSession session) {
        User user = userService.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            model.addAttribute("message", "Login successful");
            session.setAttribute("email", user.getEmail());
            return "redirect:/profileupdate";
        }

        if (user == null) {

            model.addAttribute("message", "Invalid email");

        } else {
            model.addAttribute("message", "Invalid passsword");
        }
        return "login";
    }

    @GetMapping("/profileupdate")
    public String home(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            return "redirect:/login";
        }
        model.addAttribute("email", email);
        return "profileupdate";
    }

//    @GetMapping("/search")
//    public String SearchForm() {
//        return "search";
//    }
//
//    @PostMapping("/search")
////    @GetMapping("/search")
//    public String searchUser(@RequestParam String keyword, Model model) {
//
//      List<User> users =  userService.searchUser(keyword);
//      if (users!= null) {
//          model.addAttribute("users", users);
////          model.addAttribute("email", user.getEmail());
//          return "search";
//      }
//      else return "search";
//
//
//    }
    }
