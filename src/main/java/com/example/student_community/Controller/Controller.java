package com.example.student_community.Controller;

import com.example.student_community.Model.Comments;
import com.example.student_community.Model.User;
import com.example.student_community.Repository.UserRepository;
import com.example.student_community.Services.CommentService;
import com.example.student_community.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
public class Controller {
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/createUser")
    public ResponseEntity createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/userInfo/{email}")
    public ResponseEntity<User> getUser(@PathVariable String email)
    {
        return ResponseEntity.ok(userService.findByEmail(email));

    }



    //edit profile
    @PostMapping("/editProfile/{email}")
    public ResponseEntity<String> editProfile(@PathVariable String email, @RequestBody User editUser)
    {
        return  userService.updateUser(editUser, email);
    }
    //edit profile

    //editBio
    @PostMapping("/updateBio/{email}")
    public ResponseEntity<String> updateBio(@PathVariable String email,@RequestBody String bio) {



        // Code to save the bio to your database
        return userService.updateBio(email,bio);
    }
    //editBio

    @GetMapping("/test")
    public List<Comments> test() {

            return commentService.getAllComments();

    }

    //edit pf image
    private static final String UPLOAD_DIR = "\\src\\main\\resources\\static\\profiles"; // Replace with your desired upload directory

    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file,@RequestParam String email) {
        try {
            // Generate a random UUID for uniqueness
            String randomUUID = UUID.randomUUID().toString();

            // Get original file n`ame
            String originalFileName = file.getOriginalFilename();

            // Construct new file name with random UUID
            String newFileName = randomUUID + "_" + originalFileName;

            // Create the upload directory if it doesn't exist
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String absoluteUploadDir = new File(System.getProperty("user.dir"), UPLOAD_DIR).getAbsolutePath();
            // Save the file to the upload directory
            Path filePath = Paths.get(absoluteUploadDir, newFileName);
            System.out.println(absoluteUploadDir);
            Files.write(filePath, file.getBytes());
            userService.uploadProfile(email,newFileName);
            return ResponseEntity.ok("Image uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image");
        }
    }
    //edit pf image

    //post control


    //post control


}