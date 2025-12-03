package com.example.Cookie_Session_ResponseEntity.login.service;

import com.example.Cookie_Session_ResponseEntity.login.dto.LoginRequstDto;
import com.example.Cookie_Session_ResponseEntity.login.dto.LoginResponseDto;
import com.example.Cookie_Session_ResponseEntity.login.session.SessionStore;
import com.example.Cookie_Session_ResponseEntity.user.entity.User;
import com.example.Cookie_Session_ResponseEntity.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@Transactional
public class LoginService {

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // login
    public LoginResponseDto loginAuthentication(LoginRequstDto loginRequstDto){

        User user = userRepository.findByUsername(loginRequstDto.getUsername());
        if(user == null || !user.getPassword().equals(loginRequstDto.getPassword())) {
            SessionStore.SessionStatus();
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 실패");
        }
        // 로그인 성공 시 세션 생성
        String sessionId = UUID.randomUUID().toString();
        SessionStore.put(sessionId, user);

        SessionStore.SessionStatus();
        return new LoginResponseDto("로그인 성공!", sessionId);
    }

    // logout
    public String logout(String sessionId){
        User user = SessionStore.get(sessionId);
        if(user != null) {
            SessionStore.remove(sessionId);
            SessionStore.SessionStatus();
            return "로그아웃 성공!";

        } else {
            return "잘못된 접근입니다.";
        }
    }


}