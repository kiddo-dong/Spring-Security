package com.example.Spring_Security_Jpa.user.controller;

import com.example.Spring_Security_Jpa.user.dto.UserInfoResponse;
import com.example.Spring_Security_Jpa.user.dto.UserLoginRequest;
import com.example.Spring_Security_Jpa.user.dto.UserLoginResponse;
import com.example.Spring_Security_Jpa.user.dto.UserSignUp;
import com.example.Spring_Security_Jpa.user.service.UserService;
import com.sun.security.auth.UserPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody @Validated UserSignUp userSignUp) {

        userService.signUp(userSignUp);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 로그인 -> jwt 발행
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody @Validated UserLoginRequest userLoginRequest) {

        return ResponseEntity
                .ok(userService.login(userLoginRequest));

    }

    // GET me
    @GetMapping("/me")
    public ResponseEntity<UserInfoResponse> getCurrentUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        UserInfoResponse userInfoResponse = userService.getMyInfo(userPrincipal.getName());
        return ResponseEntity.ok(userInfoResponse);
    }
}
