package com.example.student_community.Controller;

import com.example.student_community.Model.FriendDTO;
import com.example.student_community.Services.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    private FriendService friendService;
    @GetMapping("/requestFriend")
    public ResponseEntity<String> requestFriend(@RequestParam int sender, @RequestParam int receiver){
        return friendService.requestFriend(sender,receiver);
    }

    @PostMapping("/acceptFriend")
    public ResponseEntity<String> acceptFriend(@RequestParam int sender, @RequestParam int receiver){
        return friendService.acceptFriendRequest(sender,receiver);
    }

    @GetMapping("/getFriendDTO")
    public ResponseEntity<List<FriendDTO>> getFriends(@RequestParam int id, @RequestParam String input){
        return friendService.getFriendDTO(id,input);
    }

    @DeleteMapping("/deleteFriend")
    public ResponseEntity<String> deleteFriend(@RequestParam int sender, @RequestParam int receiver){
        return friendService.deleteFriend(sender,receiver);
    }
}
