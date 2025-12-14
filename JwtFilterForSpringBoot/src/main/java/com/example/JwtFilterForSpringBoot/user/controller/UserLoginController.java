package com.example.JwtFilterForSpringBoot.user.controller;

import com.example.JwtFilterForSpringBoot.user.dto.UserRequestDto;
import com.example.JwtFilterForSpringBoot.user.service.UserLoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// "api/*" 으로 들어오는 모든 요청 Filter 처리
@RestController
@RequestMapping("/api/auth")
public class UserLoginController {
    public UserLoginService userLoginService;

    public UserLoginController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    // 로그인 시
    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody UserRequestDto userRequestDto){
        String token = userLoginService.login(userRequestDto);

        // 인증 성공 시 JWT return -> client 측
        return ResponseEntity
                .ok()
                .header("Authorization", "Bearer " + token)
                .body("로그인 성공");
    }
}