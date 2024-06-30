package com.example.student_community.MailVerification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> verifyOtp(@RequestBody OtpRequest otpRequest) {
        System.out.println(otpRequest.getOtp());
        boolean isValid = otpService.verifyOtp(otpRequest.getEmail(), otpRequest.getOtp());
        if (isValid) {
            return new ResponseEntity<>("OTP verified successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid OTP!", HttpStatus.BAD_REQUEST);
        }
    }


}
