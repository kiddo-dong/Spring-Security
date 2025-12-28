package com.example.Security_User_CRUD.user.service;

import com.example.Security_User_CRUD.user.dto.UserRequestDto;
import com.example.Security_User_CRUD.user.dto.UserResponseDto;

public interface UserService {
    String addUser(UserRequestDto userRequestDto);
    public UserResponseDto findUserByUsername(String username);
}