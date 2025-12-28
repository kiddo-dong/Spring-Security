package com.example.Spring_Security_Jpa.user.controller;

import com.example.Spring_Security_Jpa.user.dto.UserSignUp;
import com.example.Spring_Security_Jpa.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody @Validated UserSignUp userSignUp) {

        userService.signUp(userSignUp);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
