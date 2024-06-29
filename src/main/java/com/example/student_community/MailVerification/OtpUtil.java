package com.example.student_community.MailVerification;

import java.security.SecureRandom;

public class OtpUtil {

    private static final String NUMERIC_CHARACTERS = "0123456789";
    private static final SecureRandom random = new SecureRandom();

    public static String generateNumericOtp(int length) {
        StringBuilder otp = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            otp.append(NUMERIC_CHARACTERS.charAt(random.nextInt(NUMERIC_CHARACTERS.length())));
        }
        return otp.toString();
    }
}