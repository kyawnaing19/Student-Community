package com.example.student_community.Services;

import com.example.student_community.Model.User;
import com.example.student_community.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<String> createUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(409).body("User already exists");
        }
        userRepository.save(user);
        return ResponseEntity.ok("Created successfully");



    }
    public User findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail( email);
        return user.orElse(null);
    }

    public List<User> searchUser(String keyword) {
        return userRepository.findByNameContaining(keyword);

    }
}
