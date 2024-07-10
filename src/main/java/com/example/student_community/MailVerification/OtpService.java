package com.example.student_community.MailVerification;

import com.example.student_community.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class OtpService {

    @Autowired
    private CheckUserRepository checkUserRepository;

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private EmailService emailService;

    public ResponseEntity<String> sendOtp(String email) {
        String otp = OtpUtil.generateNumericOtp(6);
        Otp otpEntity = new Otp();
        otpEntity.setEmail(email);
        otpEntity.setOtp(otp);
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(2);
        otpEntity.setExpirationtime(expirationTime);
        User user=checkUserRepository.findByEmail(email);

if (user==null)
{
    otpRepository.save(otpEntity);
    emailService.sendOtpEmail(email, "Your OTP Code", "Your OTP code is: " + otp);
    return ResponseEntity.ok("successful");
}
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail");
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