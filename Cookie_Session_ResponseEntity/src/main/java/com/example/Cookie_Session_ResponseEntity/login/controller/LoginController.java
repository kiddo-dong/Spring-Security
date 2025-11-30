package com.example.Cookie_Session_ResponseEntity.login.controller;

import com.example.Cookie_Session_ResponseEntity.login.dto.LoginRequstDto;
import com.example.Cookie_Session_ResponseEntity.login.dto.LoginResponseDto;
import com.example.Cookie_Session_ResponseEntity.login.service.LoginService;
import com.example.Cookie_Session_ResponseEntity.login.session.SessionStore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequstDto loginRequstDto){

        LoginResponseDto loginResponseDto = loginService.loginAuthentication(loginRequstDto);

        // Header - Cookie 주고
        // Body - Message(String)
        return ResponseEntity
                .ok()
                .header("Set-Cookie", "SESSIONID=" + loginResponseDto.getSessionId() + "; HttpOnly; Path=/")
                .body(loginResponseDto.getMessage());
    }
}