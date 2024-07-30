package com.example.student_community.Services;

import com.example.student_community.Model.Comments;
import com.example.student_community.Model.Posts;
import com.example.student_community.Repository.CommentsRepository;
import com.example.student_community.Repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentsRepository commentsRepository;
    @Autowired
    private PostsRepository postsRepository;
    public List<Comments> getComments(Integer postId) {
        Posts posts=postsRepository.findById(postId).get();
        System.out.println("This is post = "+posts);
        return commentsRepository.findComment(posts);
    }
}
