package com.example.student_community.Services;

import com.example.student_community.Model.Comments;
import com.example.student_community.Model.Posts;
import com.example.student_community.Model.User;
import com.example.student_community.Repository.CommentsRepository;
import com.example.student_community.Repository.PostsRepository;
import com.example.student_community.Repository.UserRepository;
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
    @Autowired
    private UserRepository userRepository;

    public List<Comments> getComments(Integer postId) {
        Posts posts=postsRepository.findById(postId).get();
        System.out.println("This is post = "+posts);
        return commentsRepository.findComment(posts);
    }

    public ResponseEntity createComment(int postId,int userId,int parentComId,String content) {
        Posts posts=postsRepository.findById(postId).get();
        User users=userRepository.findById(userId).get();
        Comments comment=new Comments();
        if(parentComId==0){
            comment.setContent(content);
            comment.setPost(posts);
            comment.setUser(users);
            commentsRepository.save(comment);
        }
        else {
            comment.setContent(content);
            comment.setPost(posts);
            comment.setUser(users);
            Comments com=commentsRepository.findById(parentComId).get();
            comment.setParentComment(com);
            commentsRepository.save(comment);
        }
        return ResponseEntity.ok().build();
    }
}
