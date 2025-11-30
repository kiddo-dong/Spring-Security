package com.example.Cookie_Session_ResponseEntity.user.service;

import com.example.Cookie_Session_ResponseEntity.user.entity.User;
import com.example.Cookie_Session_ResponseEntity.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // save()
    public void saveUser(User user){
        userRepository.save(user);
    }
}