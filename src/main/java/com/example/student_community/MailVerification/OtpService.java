package com.example.student_community.MailVerification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class OtpService {

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private EmailService emailService;

    public String sendOtp(String email) {
        String otp = OtpUtil.generateNumericOtp(6);
        Otp otpEntity = new Otp();
        otpEntity.setEmail(email);
        otpEntity.setOtp(otp);
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(2);
        otpEntity.setExpirationtime(expirationTime);

        otpRepository.save(otpEntity);
        emailService.sendOtpEmail(email, "Your OTP Code", "Your OTP code is: " + otp);
        return "Otp Successfully Sent";
    }

    public boolean verifyOtp(String email, String otp) {
        Otp otpEntity = otpRepository.findByEmail(email);
        System.out.println(otp);
        System.out.println("Mg Htu");

        if (otpEntity != null && otpEntity.getOtp().equals(otp) &&
                otpEntity.getExpirationtime().isAfter(LocalDateTime.now())) {
            otpRepository.delete(otpEntity);
            return true;
        }
        return false;
    }
}