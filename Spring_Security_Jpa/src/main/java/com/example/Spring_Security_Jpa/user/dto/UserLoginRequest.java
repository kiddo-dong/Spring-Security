package com.example.Spring_Security_Jpa.user.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest {
    private String username;
    private String password;
}
