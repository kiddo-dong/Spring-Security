package com.example.Spring_Security_Jpa.user.controller;

import com.example.Spring_Security_Jpa.user.dto.UserLoginRequest;
import com.example.Spring_Security_Jpa.user.dto.UserLoginResponse;
import com.example.Spring_Security_Jpa.user.dto.UserSignUp;
import com.example.Spring_Security_Jpa.user.service.UserAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserAuthController {

    private final UserAuthService userAuthService;

    public UserAuthController(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    // 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody @Validated UserSignUp userSignUp) {

        userAuthService.signUp(userSignUp);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 로그인 -> jwt 발행
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody @Validated UserLoginRequest userLoginRequest) {

        return ResponseEntity
                .ok(userAuthService.login(userLoginRequest));

    }
}
