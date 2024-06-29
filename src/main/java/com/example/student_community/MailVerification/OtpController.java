package com.example.student_community.MailVerification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OtpController {

    @Autowired
    private EmailService emailService;
    @Autowired
    private OtpService otpService;

    @GetMapping("/sendOtp")
    public String sendOtp(@RequestParam String email) {
        return otpService.sendOtp(email);
    }

    @PostMapping("/verifyOtp")
    public String verifyOtp(@RequestBody OtpRequest otpRequest) {
        System.out.println(otpRequest.getOtp());
        boolean isValid = otpService.verifyOtp(otpRequest.getEmail(), otpRequest.getOtp());
        return isValid ? "OTP verified successfully!" : "Invalid OTP!";
    }


}
