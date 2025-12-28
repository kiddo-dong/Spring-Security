package com.example.Security_User_CRUD.user.mapper;

import com.example.Security_User_CRUD.user.domain.User;
import com.example.Security_User_CRUD.user.dto.UserRequestDto;
import com.example.Security_User_CRUD.user.dto.UserResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRequestDto userRequestDto);

    UserResponseDto toResponseDto(User user);
}