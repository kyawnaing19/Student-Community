package com.example.student_community.Repository;

import com.example.student_community.Model.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friends,Integer> {
Optional<Friends> findByReceiverAndSender(int receiver,int sender);
List<Friends> findByReceiver(int id);

    @Query("SELECT fr FROM Friends  fr WHERE fr.sender= :senderId AND fr.receiver= :receiverId")
    Friends findBySenderAndReceiver(int senderId, int receiverId);



}
