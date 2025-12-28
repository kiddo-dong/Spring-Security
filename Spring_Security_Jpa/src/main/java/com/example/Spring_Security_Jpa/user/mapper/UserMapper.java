package com.example.Spring_Security_Jpa.user.mapper;

import com.example.Spring_Security_Jpa.user.domain.User;
import com.example.Spring_Security_Jpa.user.dto.UserInfoResponse;
import com.example.Spring_Security_Jpa.user.dto.UserSignUp;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUserEntity(UserSignUp userSignUp);

    UserInfoResponse toUserInfoResponse(User user);
}