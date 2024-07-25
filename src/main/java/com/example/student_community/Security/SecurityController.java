//package com.example.student_community.Security;
//
//import com.example.student_community.Model.User;
//import com.example.student_community.Repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Optional;
//
//@RestController
//public class SecurityController {
//    @Autowired
//    private BCryptPasswordEncoder encoder;
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    private UserRepository userRepository;
//
//    @PostMapping("/createNewUser")
//    public ResponseEntity createNewUser(@RequestBody LoginRequest request){
//        Optional<User> customUser=userRepository.findByEmail(request.getEmail());
//        if(customUser.isPresent()){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
//        }
//        User user=new User();
//        user.setEmail(request.getEmail());
//        user.setPassword(encoder.encode(request.getPassword()));
//        user.setPhone("1111111");
//        user.setRno("rrewrerw");
//        user.setName("adfasdf");
//        userRepository.save(user);
//        return ResponseEntity.ok("user created");
//    }
//
//
//
//
//}
