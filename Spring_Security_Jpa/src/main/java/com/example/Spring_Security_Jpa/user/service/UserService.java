package com.example.Spring_Security_Jpa.user.service;

import com.example.Spring_Security_Jpa.user.domain.User;
import com.example.Spring_Security_Jpa.user.dto.UserInfoResponse;
import com.example.Spring_Security_Jpa.user.dto.UserUpdateRequest;
import com.example.Spring_Security_Jpa.user.mapper.UserMapper;
import com.example.Spring_Security_Jpa.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // get info
    public UserInfoResponse getMyInfo(String username) {
        User user = userRepository.findByUsername(username);

        if(user == null){
            throw new IllegalStateException("User not found");
        }

        return userMapper.toUserInfoResponse(user);
    }

    // update
    public void userUpdate(String username, UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new IllegalStateException("User not found");
        }

        // user Mapping logic
        // 1차 캐시 등록으로 메소드 종료시 자동 tx.commit
        userMapper.updateUserFromRequest(userUpdateRequest, user);
    }

    // delete
    public void userDelete(String username) {
        User user = userRepository.findByUsername(username);

        if(user == null){
            throw new IllegalStateException("User not found");
        }

        userRepository.delete(user);
    }
}