package com.example.student_community.Controller;

import com.example.student_community.Model.FriendDTO;
import com.example.student_community.Model.User;
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

    @GetMapping("/getFriendRequest")
    public ResponseEntity<List<User>> getFriendsRequest(@RequestParam int id){
        return friendService.getPendingFriend(id);
    }

    @GetMapping("/getFriends")
    public ResponseEntity<List<User>> getFriends(@RequestParam int id){
        return friendService.getFriends(id);
    }

    @GetMapping("/friends-of-friends")
    public List<User> getFriendsOfFriends(@RequestParam int userId) {
        return friendService.getFriendsOfFriends(userId);
    }

    // aung myint tun
    @GetMapping("/getFriendBtn")
    public ResponseEntity<FriendDTO> getFriBtn(@RequestParam int id, @RequestParam String otherEmail)
    {
        return friendService.getFriBtn(id,otherEmail);
    }
    // aung myint tun

}
