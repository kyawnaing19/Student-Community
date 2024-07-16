package com.example.student_community.Services;

import com.example.student_community.Model.User;
import com.example.student_community.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


    public ResponseEntity<String> updateUser(User editUser, String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            // Update fields of existingUser with editUser fields
            existingUser.setName(editUser.getName());
            existingUser.setMajor(editUser.getMajor());
            existingUser.setPhone(editUser.getPhone());
            existingUser.setRno(editUser.getRno());
            existingUser.setAddress(editUser.getAddress());
            existingUser.setDOB(editUser.getDOB());
            existingUser.setGender(editUser.getGender());
            existingUser.setProfile(editUser.getProfile());


            // Save the updated user
             userRepository.save(existingUser);
             return ResponseEntity.ok("update success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();// Handle case where user with username doesn't exist
        }
    }


    public User findProfileByEmail(String email) {
        Optional<User> user = userRepository.findByEmail( email);
        return user.orElse(null);
    }

    public ResponseEntity<String> updateBio(String email,String bio)
    {
        Optional<User> user = userRepository.findByEmail( email);
        if(user.isPresent())
        {
//          user.get().setBio(bio);
            User user1=user.get();
            user1.setBio(bio);
            userRepository.save(user1);
            return ResponseEntity.ok("update success");

        }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();// Handle case where user with username doesn't exist

    }

    public ResponseEntity<String> uploadProfile(String email,String profile){
        Optional<User> user = userRepository.findByEmail( email);
        if(user.isPresent()){
            User user1=user.get();
            user1.setProfile(profile);
            userRepository.save(user1);
            return ResponseEntity.ok("upload success");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
