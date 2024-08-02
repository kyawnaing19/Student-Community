package com.example.student_community.Services;

import com.example.student_community.Model.FriendDTO;
import com.example.student_community.Model.Friends;
import com.example.student_community.Model.User;
import com.example.student_community.Repository.FriendRepository;
import com.example.student_community.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FriendService {
    @Autowired
    private UserService userService;
    @Autowired
    private FriendRepository friendRepository;
    @Autowired
    private UserRepository userRepository;

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


    public ResponseEntity<List<User>> getPendingFriend(int id){
        List<User> friends=new ArrayList<>();
        List<Friends> f=friendRepository.findByReceiverAndStatus(id,"PENDING");
        for(Friends fri:f){
            User user=userService.getUserById(fri.getSender());
            friends.add(user);
        }
        return ResponseEntity.status(HttpStatus.OK).body(friends);
    }

    public ResponseEntity<List<User>> getFriends(int id){
        List<User> friends=new ArrayList<>();
        List<Friends> f1=friendRepository.findBySenderAndStatus(id,"ACCEPTED");
        List<Friends> f2=friendRepository.findByReceiverAndStatus(id,"ACCEPTED");

        if(!f1.isEmpty())
        {
            for(Friends fri:f1){
                User user=userService.getUserById(fri.getReceiver());
                friends.add(user);
            }
        }
         if (!f2.isEmpty())
        {
            for(Friends fri:f2){
                User user=userService.getUserById(fri.getSender());
                friends.add(user);
            }
        }



        if(friends.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(friends);
        }

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



    public List<User> getFriendsOfFriends(int userId) {
        // Step 1: Find friends of the given user
        List<Integer> friendsOfUser = friendRepository.findFriendsOfUser(userId);

        // Step 2: For each friend, find their friends and exclude the original user
        List<Integer> friendsOfFriendsIds = friendsOfUser.stream()
                .flatMap(friendId -> friendRepository.findFriendsOfFriend(friendId, userId).stream())
                .distinct()
                .collect(Collectors.toList());

        // Step 3: Fetch User objects for these IDs
        return userRepository.findAllById(friendsOfFriendsIds);
    }

    //if friend
    public boolean isFriend(int oid,int mid)
    {
        Optional<Friends> a=friendRepository.findBySenderAndReceiverAndStatus(oid, mid,"ACCEPTED");
        Optional<Friends> b=friendRepository.findBySenderAndReceiverAndStatus(mid, oid,"ACCEPTED");
        if(a.isPresent()) return true;
        if(b.isPresent()) return  true;

        return false;
    }
    //if friend

    //fri of fri
public boolean isFOF(int myId,int oId)
{
    List<Integer> friendsOfUser = friendRepository.findFriendsOfUser(myId);

    // Step 2: For each friend, find their friends and exclude the original user
    List<Integer> friendsOfFriendsIds = friendsOfUser.stream()
            .flatMap(friendId -> friendRepository.findFriendsOfFriend(friendId, myId).stream())
            .distinct()
            .toList();

    return friendsOfFriendsIds.contains(oId);
}
    //fri of fri

    //aung myint tun
    public ResponseEntity<FriendDTO> getFriBtn(int id, String otherEmail) {
        User users=friendRepository.findByEmailAndExclude(otherEmail,id);

        FriendDTO dto=new FriendDTO();
        dto.setUser(users);
        Optional<Friends> a=friendRepository.findBySenderAndReceiver(id,users.getId());
        Optional<Friends> b=friendRepository.findBySenderAndReceiver(users.getId(),id);
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


        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
    //aung myint tun

}
