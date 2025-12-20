package com.example.UsernamePasswordAuthenticationFilter.user.mapper;

import com.example.UsernamePasswordAuthenticationFilter.user.domain.User;
import com.example.UsernamePasswordAuthenticationFilter.user.dto.UserRequestDto;
import com.example.UsernamePasswordAuthenticationFilter.user.dto.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRequestDto userRequestDto);

    UserResponseDto toResponseDto(User user);
}