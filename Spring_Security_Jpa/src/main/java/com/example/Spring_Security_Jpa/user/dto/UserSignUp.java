package com.example.Spring_Security_Jpa.user.dto;

import com.example.Spring_Security_Jpa.user.domain.Role;
import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUp {
    private String username;
    private String password;
    private String name;
    private Role role;
}
