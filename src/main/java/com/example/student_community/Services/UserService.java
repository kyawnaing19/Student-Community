package com.example.student_community.Services;

import com.example.student_community.Model.User;
import com.example.student_community.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public ResponseEntity createUser(User user) {
        userRepository.save(user);
        return ResponseEntity.ok("created successfully");

    }
}
