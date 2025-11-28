package com.example.Basic_Authentication.user.service;

import com.example.Basic_Authentication.user.entity.User;
import com.example.Basic_Authentication.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String saveUser(User user){
        userRepository.save(user);
        return "생성 완료";
    }

    public User findUser(String username, String password){
        // DB에서 User 검색
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        }
        // username & password 검증
        if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
            return user;
        } else {
            throw new RuntimeException("사용자 이름 및 비밀번호가 일치하지 않습니다.");
        }
    }
}