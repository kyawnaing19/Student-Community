package com.example.student_community.Services;

import com.example.student_community.DTO.PostWithParentDTO;
import com.example.student_community.Model.Images;
import com.example.student_community.Model.Posts;
import com.example.student_community.Model.User;
import com.example.student_community.Repository.FriendRepository;
import com.example.student_community.Repository.ImageRepository;
import com.example.student_community.Repository.PostsRepository;
import com.example.student_community.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostsService {
    @Autowired
    private PostsRepository postsRepository;
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private FriendRepository friendRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FriendService friendService;


    public Posts getPost(int id){
        return postsRepository.findById(id).get();
    }


    public ResponseEntity<String> createPostWithImages(Posts post, List<Images> images) {
        // Save the post
        Posts savedPost = postsRepository.save(post);
        if(!images.isEmpty()){
            for (Images image : images) {

                image.setPost(savedPost);
            }
            imageRepository.saveAll(images);
        }
        // Set the post reference in images


        // Save the images


        return ResponseEntity.ok("Post created successfully");
    }


    private static final String UPLOAD_DIR = "\\src\\main\\resources\\static\\PostImages"; // Replace with your desired upload directory

    public void saveImages(List<MultipartFile> images) throws IOException {
        for (MultipartFile image : images) {
            String absoluteUploadDir = new File(System.getProperty("user.dir"), UPLOAD_DIR).getAbsolutePath();
            // Save the file to the upload directory
            Path filePath = Paths.get(absoluteUploadDir, image.getOriginalFilename());
            System.out.println(absoluteUploadDir);
            Files.write(filePath, image.getBytes());
        }
    }


    //NEWFEED
    public List<PostWithParentDTO> getNewsFeedPosts(int userId) {
        List<Integer> friendIds = friendRepository.findFriendsOfUser(userId);
        List<String> audiences = new ArrayList<>();
        audiences.add("Public");
        audiences.add("Friends");
        audiences.add("FOFriends");

        List<Posts> posts = postsRepository.findNewsFeedPosts(friendIds, audiences);
        return posts.stream().map(this::mapToPostWithParentDTO).collect(Collectors.toList());
    }



    //getMyWall
//    public ResponseEntity<List<Posts>> getMyWall(int id) {
//        User user=userRepository.findById(id).get();
//        List<Posts> posts= postsRepository.findMyWall(user);
//        return ResponseEntity.ok(posts);
//    }
    //getMyWall

    //get other wall
    public List<PostWithParentDTO> getOtherWallPosts(int mid, int oid, User user, List<String> audiences) {
        List<Posts> userPosts = postsRepository.findOtherWall(user, audiences);
        return userPosts.stream().map(this::mapToPostWithParentDTO).collect(Collectors.toList());
    }
    //get other wall





    public List<PostWithParentDTO> getAllPostsByUser(int userId) {
        List<Posts> userPosts = postsRepository.findAllPostsByUserId(userId);
        return userPosts.stream().map(this::mapToPostWithParentDTO).collect(Collectors.toList());
    }

    private PostWithParentDTO mapToPostWithParentDTO(Posts post) {
        PostWithParentDTO dto = new PostWithParentDTO();
        dto.setId(post.getId());
        dto.setContent(post.getContent());
        dto.setAudience(post.getAudience());
        dto.setCreatedAt(post.getCreated_at());
        dto.setUserName(post.getUserName());
        dto.setUserEmail(post.getEmail());
        dto.setUserProfile(post.getProfile());
        dto.setImages(post.getImages());
        dto.setLikes(post.getLike());
        dto.setComments(post.getComments());

        if (post.getParentId() != null) {
            PostWithParentDTO.ParentPostDTO parentPostDTO = new PostWithParentDTO.ParentPostDTO();
            parentPostDTO.setId(post.getParentId().getId());
            parentPostDTO.setContent(post.getParentId().getContent());
            parentPostDTO.setAudience(post.getParentId().getAudience());
            parentPostDTO.setCreatedAt(post.getParentId().getCreated_at());
            parentPostDTO.setUserName(post.getParentId().getUserName());
            parentPostDTO.setUserEmail(post.getParentId().getEmail());
            parentPostDTO.setUserProfile(post.getParentId().getProfile());
            parentPostDTO.setImages(post.getParentId().getImages());
            parentPostDTO.setComments(post.getParentId().getComments());
           parentPostDTO.setLikes(post.getParentId().getLike());
            dto.setParentPost(parentPostDTO);

        }

        return dto;
    }



}
