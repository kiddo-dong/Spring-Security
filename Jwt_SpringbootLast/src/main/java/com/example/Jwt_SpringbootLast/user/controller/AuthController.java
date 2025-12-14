package com.example.Jwt_SpringbootLast.user.controller;

import com.example.Jwt_SpringbootLast.user.dto.SignupRequest;
import com.example.Jwt_SpringbootLast.user.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    //DI & Auto wired
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(
            @RequestBody SignupRequest request) {

        authService.signup(request);

        // HTTP 상태 코드 전송
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}