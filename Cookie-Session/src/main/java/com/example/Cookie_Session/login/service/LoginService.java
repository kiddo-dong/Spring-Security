package com.example.Cookie_Session.login.service;

import com.example.Cookie_Session.login.dto.LoginRequstDto;
import com.example.Cookie_Session.login.dto.LoginResponseDto;
import com.example.Cookie_Session.login.session.SessionStore;
import com.example.Cookie_Session.user.entity.User;
import com.example.Cookie_Session.user.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;
@Service
public class LoginService {

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void loginAuthentication(LoginRequstDto loginRequstDto, HttpServletResponse response){

        User user = userRepository.findByUsername(loginRequstDto.getUsername());
        if(user == null || !user.getPassword().equals(loginRequstDto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 실패");
        }

        // UUID (랜덤 Hash값으로 SESSIONID 지정)
        String sessionId = UUID.randomUUID().toString();
        SessionStore.put(sessionId, user);

        // Cookie 생성자 호출
        Cookie cookie = new Cookie("SESSIONID", sessionId);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        // Service 로직에서 응답시 Header의 Cookie 설정


    }
}