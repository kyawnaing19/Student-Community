package com.example.student_community.Services;

import com.example.student_community.Model.Likes;
import com.example.student_community.Model.Posts;
import com.example.student_community.Model.User;
import com.example.student_community.Repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;
    public ResponseEntity<String> addLike(Likes likes){
        System.out.println(likes);
        return ResponseEntity.ok("Success");
    }
}
