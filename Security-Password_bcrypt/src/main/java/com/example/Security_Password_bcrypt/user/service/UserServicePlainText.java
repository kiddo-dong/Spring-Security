package com.example.Security_Password_bcrypt.user.service;

import com.example.Security_Password_bcrypt.user.entity.User;
import com.example.Security_Password_bcrypt.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServicePlainText {
    private final UserRepository userRepository;

    public UserServicePlainText(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUserPlainText(User user){
        userRepository.save(user);
    }
}