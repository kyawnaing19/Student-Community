package com.example.student_community.Controller;

import com.example.student_community.Model.Posts;
import com.example.student_community.Services.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    private PostsService postsService;
    @GetMapping("/getPosts")
    public Posts getPosts(@RequestParam int id){
        return postsService.getPost(id);
    }
}
