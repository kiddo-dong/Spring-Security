package com.example.Spring_Security_Jpa.user.service;

import com.example.Spring_Security_Jpa.user.domain.Role;
import com.example.Spring_Security_Jpa.user.domain.User;
import com.example.Spring_Security_Jpa.user.dto.UserSignUp;
import com.example.Spring_Security_Jpa.user.mapper.UserMapper;
import com.example.Spring_Security_Jpa.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

    private final PasswordEncoder PasswordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, UserMapper userMapper) {
        this.PasswordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void signUp(UserSignUp userSignUp) {

        // user 중복 비교
        User user = userRepository.findByUsername(userSignUp.getUsername());

        if (user == null) {

            User newUser = userMapper.toUserEntity(userSignUp);
            newUser.setPassword(PasswordEncoder.encode(userSignUp.getPassword()));
            newUser.setRole(Role.ROLE_USER);
            userRepository.save(newUser);

        } else {
            // 예외처리
            throw new RuntimeException("이미 존재하는 user 입니다.");
        }
    }
}