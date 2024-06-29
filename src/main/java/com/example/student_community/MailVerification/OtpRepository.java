package com.example.student_community.MailVerification;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<Otp, Long> {
    Otp findByEmail(String email);
}
