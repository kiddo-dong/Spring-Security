package com.example.Jwt_SpringbootLast.user.dto;

public class SignupRequest {
    private String loginId;
    private String password;

    public String getLoginId() {
        return loginId;
    }

    public String getPassword() {
        return password;
    }
}