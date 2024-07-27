package com.example.student_community.DTO;

import com.example.student_community.Model.Images;
import com.example.student_community.Model.Posts;
import lombok.Data;

import java.util.List;

@Data
public class PostRequest {
    private int user_id;
    private Posts post;
    private List<Images> images;
}
