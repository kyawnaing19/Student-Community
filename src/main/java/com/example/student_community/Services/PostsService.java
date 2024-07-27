package com.example.student_community.Services;

import com.example.student_community.Model.Posts;
import com.example.student_community.Repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostsService {
    @Autowired
    private PostsRepository postsRepository;

    public Posts getPost(int id){
        return postsRepository.findById(id).get();
    }
}