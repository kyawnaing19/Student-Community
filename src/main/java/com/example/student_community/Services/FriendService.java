package com.example.student_community.Services;

import com.example.student_community.Model.Friends;
import com.example.student_community.Model.User;
import com.example.student_community.Repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService
{
    @Autowired
    private FriendRepository friendRepository;

    public String getFriendRequestStatus(int senderId, int receiverId) {
        Friends friendRequest = friendRepository.findBySenderAndReceiver(senderId, receiverId);
        return (friendRequest != null) ? friendRequest.getStatus() : "none";
    }

    public List<Friends> searchUserById(int idd) {
        return friendRepository.findByReceiver(idd);

    }
}
