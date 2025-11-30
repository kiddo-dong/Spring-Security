package com.example.Cookie_Session.login.controller;

import com.example.Cookie_Session.login.dto.LoginRequstDto;
import com.example.Cookie_Session.login.dto.LoginResponseDto;
import com.example.Cookie_Session.login.service.LoginService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final LoginService loginService;


    // Tomcat(HttpServletRequest/HttpServletResponse) -> DispatcherSerblet -> Controller
    //
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    // HttpServletResponse ???????
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequstDto loginRequstDto,
                                                  HttpServletResponse response){
        loginService.loginAuthentication(loginRequstDto, response);
        return ResponseEntity.ok(new LoginResponseDto("로그인 성공"));
    }
}