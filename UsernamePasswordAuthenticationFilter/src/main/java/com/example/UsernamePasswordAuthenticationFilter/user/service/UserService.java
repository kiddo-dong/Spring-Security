package com.example.UsernamePasswordAuthenticationFilter.user.service;

import com.example.UsernamePasswordAuthenticationFilter.user.dto.UserRequestDto;
import com.example.UsernamePasswordAuthenticationFilter.user.dto.UserResponseDto;

public interface UserService {
    String addUser(UserRequestDto userRequestDto);
    public UserResponseDto findUserByUsername(String username);
}