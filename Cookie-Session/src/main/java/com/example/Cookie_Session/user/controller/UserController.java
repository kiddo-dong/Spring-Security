package com.example.Cookie_Session.user.controller;

import com.example.Cookie_Session.user.entity.User;
import com.example.Cookie_Session.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody User user){

        userService.saveUser(user);

        return ResponseEntity
                .ok("완료되었습니다.");
    }

}