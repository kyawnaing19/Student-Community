package com.example.student_community.Services;

import com.example.student_community.Model.Images;
import com.example.student_community.Model.Posts;
import com.example.student_community.Repository.ImageRepository;
import com.example.student_community.Repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class PostsService {
    @Autowired
    private PostsRepository postsRepository;
    @Autowired
    private ImageRepository imageRepository;

    public Posts getPost(int id){
        return postsRepository.findById(id).get();
    }


    public ResponseEntity<String> createPostWithImages(Posts post, List<Images> images) {
        // Save the post
        Posts savedPost = postsRepository.save(post);
        if(!images.isEmpty()){
            for (Images image : images) {

                image.setPost(savedPost);
            }
            imageRepository.saveAll(images);
        }
        // Set the post reference in images


        // Save the images


        return ResponseEntity.ok("Post created successfully");
    }


    private static final String UPLOAD_DIR = "\\src\\main\\resources\\static\\PostImages"; // Replace with your desired upload directory

    public void saveImages(List<MultipartFile> images) throws IOException {
        for (MultipartFile image : images) {
            String absoluteUploadDir = new File(System.getProperty("user.dir"), UPLOAD_DIR).getAbsolutePath();
            // Save the file to the upload directory
            Path filePath = Paths.get(absoluteUploadDir, image.getOriginalFilename());
            System.out.println(absoluteUploadDir);
            Files.write(filePath, image.getBytes());
        }
    }

}
