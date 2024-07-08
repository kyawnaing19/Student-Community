package com.example.student_community.MailVerification;

import com.example.student_community.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    @Autowired
    private UserRepository userRepository;

    public boolean isUserRegistered(User user) {
        return userRepository.findByName(user.getName()) != null;
    }

    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    public void registerUser(User user) {
        userRepository.save(user);

    }

}
