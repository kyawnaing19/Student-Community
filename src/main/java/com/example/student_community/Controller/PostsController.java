package com.example.student_community.Controller;

import com.example.student_community.DTO.PostRequest;
import com.example.student_community.Model.Images;
import com.example.student_community.Model.Posts;
import com.example.student_community.Model.User;
import com.example.student_community.Repository.UserRepository;
import com.example.student_community.Services.PostsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    private PostsService postsService;
    @Autowired
    private UserRepository UserRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/getPosts")
    public Posts getPosts(@RequestParam int id){
        return postsService.getPost(id);
    }

    @PostMapping("/createPost")
    public ResponseEntity<String> createPostWithImages(@RequestPart("postRequest") String postRequestJson, @RequestPart(value = "images", required = false) List<MultipartFile> imageFiles) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        PostRequest postRequest;
        try {
            postRequest = objectMapper.readValue(postRequestJson, PostRequest.class);
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body("Invalid JSON data");
        }
        User user=userRepository.findById(postRequest.getUser_id()).get();
        Posts posta=postRequest.getPost();
        posta.setUser(user);
        List<Images> images = postRequest.getImages();

        if(imageFiles!=null){
            postsService.saveImages(imageFiles);
        }

        return postsService.createPostWithImages(posta, images);
    }

    @GetMapping("/getNewFeeds")
    public ResponseEntity<List<Posts>> getNewsFeeds(@RequestParam int id){
        return postsService.getNewFeed(id);
    }

    @GetMapping("/getMyWall")
    public ResponseEntity<List<Posts>> getMyWall(@RequestParam int id)
    {
        return  postsService.getMyWall(id);
    }


//    @GetMapping("/getOtherWall")
//    public ResponseEntity<List<Posts>> getOtherWall(@RequestParam int id)
//    {
//        return  postsService.getOtherWall(id);
//    }



}
