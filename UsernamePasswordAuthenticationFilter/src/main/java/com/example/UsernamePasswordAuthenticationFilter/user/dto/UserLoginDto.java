package com.example.UsernamePasswordAuthenticationFilter.user.dto;

import lombok.Data;

@Data
public class UserLoginDto {
    private String username;
    private String password;
}