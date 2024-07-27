package com.example.student_community.Services;

import com.example.student_community.Model.Comments;
import com.example.student_community.Repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentsRepository commentsRepository;
    public List<Comments> getAllComments() {
        return commentsRepository.findAll();
    }
}
