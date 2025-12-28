package com.example.Security_User_CRUD.user.dto;


import lombok.Data;

@Data
public class UserRequestDto {
    private String username;
    private String password;
    private String name;
    private String age;
}
