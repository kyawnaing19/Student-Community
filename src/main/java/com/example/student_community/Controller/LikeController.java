package com.example.student_community.Controller;

import com.example.student_community.Model.Likes;
import com.example.student_community.Services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/likes")
public class LikeController {
    @Autowired
    private LikeService likeService;
    @GetMapping("/addLike")
    public ResponseEntity addLike(@RequestParam Integer userId, @RequestParam Integer postId) {
       return likeService.addLike(userId, postId);
    }

    @DeleteMapping("/deleteLike")
    public ResponseEntity deleteLike(@RequestParam Integer userId, @RequestParam Integer postId) {
        return likeService.deleteLike(userId, postId);
    }
}
