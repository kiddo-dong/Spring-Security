package com.example.Basic_Authentication.user.controller;

import com.example.Basic_Authentication.user.entity.User;
import com.example.Basic_Authentication.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user){
        userService.saveUser(user);
        return ResponseEntity
                .ok("성공적으로 저장됨");
    }

    @GetMapping
    public ResponseEntity<String> getUser(@RequestHeader("Authorization") String auth) {
        if (auth == null || !auth.startsWith("Basic ")) {
            return ResponseEntity.status(400).body("Invalid Authorization header");
        }
        // base 64 Decoding
        String base64Credentials = auth.substring("Basic ".length());
        String decoded = new String(Base64.getDecoder().decode(base64Credentials));

        String[] parts = decoded.split(":");
        String username = parts[0];
        String password = parts[1];

        // Service 조회
        User user = userService.findUser(username,password);

        return ResponseEntity
                .ok("hello " + user.getUsername());
    }
}
