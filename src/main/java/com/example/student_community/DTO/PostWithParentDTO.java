package com.example.student_community.DTO;

import com.example.student_community.Model.Comments;
import com.example.student_community.Model.Images;
import com.example.student_community.Model.Likes;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class PostWithParentDTO {
    private int id;
    private String content;
    private String audience;
    private LocalDateTime createdAt;
    private String userName;
    private String userEmail;
    private String userProfile;
    private List<Images> images;
    private List<Likes> likes;
    private List<Comments> comments;
    private ParentPostDTO parentPost;

    // Getters and Setters

    @Data
    public static class ParentPostDTO {
        private int id;
        private String content;
        private String audience;
        private LocalDateTime createdAt;
        private String userName;
        private String userEmail;
        private String userProfile;
        private List<Images> images;
        private List<Comments> comments;

        // Getters and Setters
    }
}

