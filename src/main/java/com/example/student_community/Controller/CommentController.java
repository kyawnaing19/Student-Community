package com.example.student_community.Controller;

import com.example.student_community.Model.Comments;
import com.example.student_community.Model.Posts;
import com.example.student_community.Repository.CommentsRepository;
import com.example.student_community.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
@RequestMapping("/comments")
public class CommentController {

@Autowired
private CommentsRepository commentsRepository;

@Autowired
private CommentService commentService;

    @GetMapping("/getComments")
    public ResponseEntity<List<Comments>> getComments(@RequestParam int postId)
    {
        System.out.println("Hello = "+commentService.getComments(postId));
        return  ResponseEntity.ok(commentService.getComments(postId));
    }

    @GetMapping("/addComment")
    public ResponseEntity addComment(@RequestParam int postId, @RequestParam String content, @RequestParam int parentComId,@RequestParam int userId)
    {
        return  ResponseEntity.ok(commentService.createComment(postId,userId,parentComId,content));
    }

}
