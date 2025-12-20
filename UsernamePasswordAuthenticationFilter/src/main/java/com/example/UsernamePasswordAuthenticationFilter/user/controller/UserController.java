package com.example.UsernamePasswordAuthenticationFilter.user.controller;

import com.example.UsernamePasswordAuthenticationFilter.user.dto.UserRequestDto;
import com.example.UsernamePasswordAuthenticationFilter.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController{

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // POST(Create)
    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody UserRequestDto userRequestDto){
        String result = userService.addUser(userRequestDto);
        return ResponseEntity.ok(result);
    }

    // GET(Read)
    /*
    @GetMapping
    public ResponseEntity<UserResponseDto> findUserByName(){
    }
     */
}
