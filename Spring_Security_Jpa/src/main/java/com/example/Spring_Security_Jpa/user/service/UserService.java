package com.example.Spring_Security_Jpa.user.service;

import com.example.Spring_Security_Jpa.global.jwt.JwtProvider;
import com.example.Spring_Security_Jpa.user.domain.Role;
import com.example.Spring_Security_Jpa.user.domain.User;
import com.example.Spring_Security_Jpa.user.dto.UserInfoResponse;
import com.example.Spring_Security_Jpa.user.dto.UserLoginRequest;
import com.example.Spring_Security_Jpa.user.dto.UserLoginResponse;
import com.example.Spring_Security_Jpa.user.dto.UserSignUp;
import com.example.Spring_Security_Jpa.user.mapper.UserMapper;
import com.example.Spring_Security_Jpa.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

    private final PasswordEncoder PasswordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtProvider jwtProvider;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, UserMapper userMapper, JwtProvider jwtProvider) {
        this.PasswordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.jwtProvider = jwtProvider;
    }

    // 회원가입 (bcrypt 암호화)
    public void signUp(UserSignUp userSignUp) {

        if(userRepository.existsByUsername(userSignUp.getUsername())){
            throw new IllegalStateException("Username already exists");
        }

        String bcryptPassword = PasswordEncoder.encode(userSignUp.getPassword());

        User user = User.builder()
                .username(userSignUp.getUsername())
                .password(bcryptPassword)
                .name(userSignUp.getName())
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(user);
    }

    // 로그인 -> jwt 발행
    public UserLoginResponse login(UserLoginRequest userLoginRequest){
        User user = userRepository.findByUsername(userLoginRequest.getUsername());

        // 예외 검증 후 jwt 발행
        if(user == null){
            throw new IllegalStateException("User not found");
        }
        if(!PasswordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())){
            throw new IllegalStateException("Wrong password");
        }

        String accessToken = jwtProvider.createToken(user.getUsername(), user.getRole().name());
        return new UserLoginResponse(accessToken);
    }

    public UserInfoResponse getMyInfo(String username) {
        User user = userRepository.findByUsername(username);

        if(user == null){
            throw new IllegalStateException("User not found");
        }

        return userMapper.toUserInfoResponse(user);
    }
}