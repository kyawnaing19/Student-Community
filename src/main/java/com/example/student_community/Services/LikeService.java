package com.example.student_community.Services;

import com.example.student_community.Model.Likes;
import com.example.student_community.Model.Posts;
import com.example.student_community.Model.User;
import com.example.student_community.Repository.LikeRepository;
import com.example.student_community.Repository.PostsRepository;
import com.example.student_community.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostsRepository postsRepository;


    public ResponseEntity<String> addLike(Integer userId, Integer postId){
        User user=userRepository.findById(userId).get();
        Posts post=postsRepository.findById(postId).get();
        Likes likes=new Likes();
        likes.setUser(user);
        likes.setPost(post);
        likeRepository.save(likes);
        return ResponseEntity.ok("Success");
    }


    public ResponseEntity deleteLike(Integer userId,Integer postId){
        User user=userRepository.findById(userId).get();
        Posts post=postsRepository.findById(postId).get();
        Likes lk=likeRepository.findByUserAndPost(user,post);
        likeRepository.deleteById(lk.getId());
        return ResponseEntity.ok("Success");
    }
}
