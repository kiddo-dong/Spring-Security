package com.example.Security_Password_bcrypt.user.controller;

import com.example.Security_Password_bcrypt.user.entity.User;
import com.example.Security_Password_bcrypt.user.service.UserServiceBcrypt;
import com.example.Security_Password_bcrypt.user.service.UserServicePlainText;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserServicePlainText userServicePlainText;
    private final UserServiceBcrypt userServiceBcrypt;

    public UserController(UserServicePlainText userServicePlainText, UserServiceBcrypt userServiceBcrypt) {
        this.userServicePlainText = userServicePlainText;
        this.userServiceBcrypt = userServiceBcrypt;
    }

    // 평문 저장
    @PostMapping("/plain")
    public ResponseEntity<String> addUserPlainText(@RequestBody User user){
        userServicePlainText.addUserPlainText(user);
        return ResponseEntity
                .ok("ok");
    }

    @PostMapping("/bcrypt")
    public ResponseEntity<String> addUserBcrypt(@RequestBody User user){
        userServiceBcrypt.addUserBcrypt(user);
        return ResponseEntity
                .ok("ok");
    }
}
