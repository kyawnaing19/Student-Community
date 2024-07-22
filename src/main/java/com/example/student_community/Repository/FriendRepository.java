package com.example.student_community.Repository;

import com.example.student_community.Model.Friends;
import com.example.student_community.Model.User;
import org.eclipse.angus.mail.imap.protocol.UIDSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<Friends, Integer> {
     boolean existsBySenderAndReceiver(int sender, int receiver);
    @Query("SELECT e FROM User e WHERE e.name LIKE %:keyword% AND e.id != :id")
    List<User> findByNameAndExclude(String keyword, int id);

   Optional<Friends> findBySenderAndReceiver(int sender, int receiver);
}
