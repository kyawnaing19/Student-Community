package com.example.student_community.Controller;

import com.example.student_community.Model.Likes;
import com.example.student_community.Services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class LikeController {
    @Autowired
    private LikeService likeService;
    @GetMapping("/addLike")
    public ResponseEntity<String> addLike(@RequestBody Likes like) {
        System.out.println(like);
        return likeService.addLike(like);
    }
}
