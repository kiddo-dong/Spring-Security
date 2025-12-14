package com.example.Jwt_SpringbootLast.user.service;

import com.example.Jwt_SpringbootLast.user.domain.User;
import com.example.Jwt_SpringbootLast.user.dto.SignupRequest;
import com.example.Jwt_SpringbootLast.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Sign Up Service
    // loginId, password
    public void signup(SignupRequest request) {

        // 아이디 중복 여부 검사
        if (userRepository.existsByLoginId(request.getLoginId())) {
            throw new IllegalArgumentException("이미 존재하는 아이디");
        }

        // password encoded
        String encodedPassword =
                passwordEncoder.encode(request.getPassword());

        // 요청의 loginId와 엔코딩된 User의 pw주입
        User user = new User(
                request.getLoginId(),
                encodedPassword,
                request.
        );

        // 최종적으로 user 생성
        userRepository.save(user);
    }
}
