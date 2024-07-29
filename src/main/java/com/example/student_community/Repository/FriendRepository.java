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

    Optional<Friends> findBySenderAndReceiverAndStatus(int sender,int receiver, String status);

   Optional<Friends> findBySenderAndReceiver(int sender, int receiver);

   List<Friends> findByReceiverAndStatus(int receiver, String status);

   List<Friends> findBySenderAndStatus(int sender, String status);

    // Query to find friends of a given user
    @Query("SELECT DISTINCT " +
            "CASE " +
            "    WHEN f.sender = :userId THEN f.receiver " +
            "    ELSE f.sender " +
            "END " +
            "FROM Friends f " +
            "WHERE f.status = 'ACCEPTED' " +
            "  AND (f.sender = :userId OR f.receiver = :userId)")
    List<Integer> findFriendsOfUser(int userId);

    // Query to find friends of friends, excluding the original user
    @Query("SELECT DISTINCT " +
            "CASE " +
            "    WHEN f.sender = :friendId THEN f.receiver " +
            "    ELSE f.sender " +
            "END " +
            "FROM Friends f " +
            "WHERE f.status = 'ACCEPTED' " +
            "  AND (f.sender = :friendId OR f.receiver = :friendId) " +
            "  AND (f.sender != :originalUserId AND f.receiver != :originalUserId)")
    List<Integer> findFriendsOfFriend(int friendId, int originalUserId);
}
