package com.example.Spring_Security_Jpa.user.mapper;

import com.example.Spring_Security_Jpa.user.domain.User;
import com.example.Spring_Security_Jpa.user.dto.UserInfoResponse;
import com.example.Spring_Security_Jpa.user.dto.UserSignUp;
import com.example.Spring_Security_Jpa.user.dto.UserUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUserEntity(UserSignUp userSignUp);
    UserInfoResponse toUserInfoResponse(User user);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromRequest(UserUpdateRequest dto, @MappingTarget User user);
}