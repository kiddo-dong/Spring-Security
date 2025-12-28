package com.example.Spring_Security_Jpa.user.dto;

import com.example.Spring_Security_Jpa.user.domain.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserInfoResponse {
    private Long id;
    private String username;
    private String name;
    private Role role;
}