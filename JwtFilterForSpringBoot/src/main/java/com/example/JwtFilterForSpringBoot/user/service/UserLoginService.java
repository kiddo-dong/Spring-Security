package com.example.JwtFilterForSpringBoot.user.service;

import com.example.JwtFilterForSpringBoot.filter.JwtUtil;
import com.example.JwtFilterForSpringBoot.user.domain.User;
import com.example.JwtFilterForSpringBoot.user.dto.UserRequestDto;
import com.example.JwtFilterForSpringBoot.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserLoginService {
    private final UserRepository userRepository;

    public UserLoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 암호화(bcrypt)된 DB PW 검증
    public String login(UserRequestDto userRequestDto){
        User user = userRepository.findByName(userRequestDto.getName());

        if(user == null || !user.getPassword().equals(userRequestDto.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 실패");
        }

        try {
            return JwtUtil.createToken(user.getId());
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "JWT 생성 오류");
        }
    }

}
