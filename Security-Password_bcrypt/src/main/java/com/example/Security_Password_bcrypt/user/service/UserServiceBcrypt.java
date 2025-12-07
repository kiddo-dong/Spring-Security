package com.example.Security_Password_bcrypt.user.service;

import com.example.Security_Password_bcrypt.user.SecurityConfig;
import com.example.Security_Password_bcrypt.user.entity.User;
import com.example.Security_Password_bcrypt.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

@Service
@Transactional
public class UserServiceBcrypt {
    private final UserRepository userRepository;
    private final SecurityConfig securityConfig;

    public UserServiceBcrypt(UserRepository userRepository, SecurityConfig securityConfig) {
        this.userRepository = userRepository;
        this.securityConfig = securityConfig;
    }

    public void addUserBcrypt(User user){
        // bcrypt 알고리즘 수행 한 Password
        String bcryptPassword = securityConfig.passwordEncoder().encode(user.getPassword());
        user.setPassword(bcryptPassword);
        userRepository.save(user);
    }
}
