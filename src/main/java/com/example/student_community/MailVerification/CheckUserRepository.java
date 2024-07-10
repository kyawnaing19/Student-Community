package com.example.student_community.MailVerification;

import com.example.student_community.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckUserRepository  extends JpaRepository<User,Integer> {
    User findByEmail(String email);
}
