package com.example.student_community.Repository;

import com.example.student_community.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    List<User> findByNameContaining(String keyword);


    // User findByEmailAndPassword(String email, String password);
}
