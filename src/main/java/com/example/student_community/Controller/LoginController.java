package com.example.student_community.Controller;

import com.example.student_community.Model.User;
import com.example.student_community.Repository.UserRepository;
import com.example.student_community.Services.LoginService;
import com.example.student_community.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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


//@GetMapping("/searchFriend")
//public String search(@RequestParam String query,Model model)
//{
//    model.addAttribute("input",query);
//    return "search";
//}

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password,Model model,HttpSession session) {
        User user = userService.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            model.addAttribute("message", "Login successful");
            session.setAttribute("email", user.getEmail());
            session.setAttribute("id", user.getId());
            return "redirect:/home";
        }

        if (user == null) {

            model.addAttribute("message", "Invalid email");

        } else {
            model.addAttribute("message", "Invalid passsword");
        }
        return "login";
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        int id=(Integer) session.getAttribute("id");
        if (email == null) {
            return "redirect:/login";
        }
        model.addAttribute("email", email);
        model.addAttribute("id", id);
        return "home";
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            return "redirect:/login";
        }
        model.addAttribute("email", email);
        return "profile";
    }


    //friend operation
    @GetMapping("/friend")
    public String friend(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
//        int id=(Integer) session.getAttribute("id");
        if (email == null) {
            return "redirect:/login";
        }
        model.addAttribute("email", email);
       // model.addAttribute("id", id);
        return "friendtest";
    }


    @GetMapping("/friendRequest")
    public String friendRequest(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
//        int id=(Integer) session.getAttribute("id");
        if (email == null) {
            return "redirect:/login";
        }
        model.addAttribute("email", email);
        // model.addAttribute("id", id);
        return "friendRequestForm";
    }
    //friend operation
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
