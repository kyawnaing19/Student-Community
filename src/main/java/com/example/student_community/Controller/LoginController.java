package com.example.student_community.Controller;

import com.example.student_community.Model.User;
import com.example.student_community.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,@RequestParam String password, Model model) {
        User user = userService.findByEmail( email);
        if (user != null && user.getPassword().equals(password)) {
            model.addAttribute("message", "Login successful");
            return "index";
        }
        //else {
//
//            if(user != null)
//            model.addAttribute("message", "Invalid email");
//            if(user.getPassword().equals(password))
//                model.addAttribute("message", "Invalid passsword");
//            return "login";
//        }
        else {
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    model.addAttribute("message", "true");
                } else {
                    model.addAttribute("message", "Invalid password");
                }
            } else {
                model.addAttribute("message", "Invalid email or password");
            }
            return "login";
        }
    }
    @GetMapping("/search")
    public String SearchForm() {
        return "search";
    }

    @PostMapping("/search")
//    @GetMapping("/search")
    public String searchUser(@RequestParam String keyword, Model model) {

      List<User> users =  userService.searchUser(keyword);
      if (users!= null) {
          model.addAttribute("users", users);
//          model.addAttribute("email", user.getEmail());
          return "search";
      }
      else return "search";


    }
}
