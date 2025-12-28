package com.example.Spring_Security_Jpa.user.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {
    private String messsage;
    private String description;
}
