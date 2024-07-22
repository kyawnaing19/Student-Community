package com.example.student_community.Services;

import com.example.student_community.Model.FriendDTO;
import com.example.student_community.Model.Friends;
import com.example.student_community.Model.User;
import com.example.student_community.Repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FriendService {
    @Autowired
    private FriendRepository friendRepository;

    public ResponseEntity<String> requestFriend(int sender_id, int receiver_id){
        if(friendRepository.existsBySenderAndReceiver(sender_id, receiver_id)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("already requested");
        }
        Friends fri=new Friends();
        fri.setSender(sender_id);
        fri.setReceiver(receiver_id);
        fri.setStatus("PENDING");
        friendRepository.save(fri);
        return ResponseEntity.status(HttpStatus.OK).body("Friend request sent");
    }


    public ResponseEntity<String> acceptFriendRequest(int sender,int receiver) {
        Friends fri = friendRepository.findBySenderAndReceiver(sender,receiver)
                .orElseThrow(() -> new RuntimeException("Friend request not found"));
        fri.setStatus("ACCEPTED");
        friendRepository.save(fri);
        return ResponseEntity.status(HttpStatus.OK).body("Friend request accepted");
    }

    public ResponseEntity<String> deleteFriend(int sender_id, int receiver_id) {
        Optional<Friends> a=friendRepository.findBySenderAndReceiver(sender_id, receiver_id);
        Optional<Friends> b=friendRepository.findBySenderAndReceiver(receiver_id,sender_id);
        if(a.isPresent())
            friendRepository.deleteById(a.get().getId());
        if(b.isPresent())
            friendRepository.delete(b.get());
        return ResponseEntity.status(HttpStatus.OK).body("Friend request deleted");
    }




    public ResponseEntity<List<FriendDTO>> getFriendDTO(int id, String input) {
        List<User> users=friendRepository.findByNameAndExclude(input,id);
        List<FriendDTO> friends=new ArrayList<>();
        for(User user:users){
            FriendDTO dto=new FriendDTO();
            dto.setUser(user);
           Optional<Friends> a=friendRepository.findBySenderAndReceiver(id,user.getId());
           Optional<Friends> b=friendRepository.findBySenderAndReceiver(user.getId(),id);
           if(a.isPresent()){
               dto.setStatus("Cancel Request");
               if(a.get().getStatus().equals("ACCEPTED"))
                   dto.setStatus("Friend");
           }else if(b.isPresent()){
               dto.setStatus("Confirm");
               if(b.get().getStatus().equals("ACCEPTED"))
                   dto.setStatus("Friend");
           }else{
               dto.setStatus("Add Friend");
           }
            friends.add(dto);
        }
        return ResponseEntity.status(HttpStatus.OK).body(friends);
    }
}
