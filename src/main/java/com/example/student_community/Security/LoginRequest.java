package com.example.student_community.Security;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
