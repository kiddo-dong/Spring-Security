package com.example.Security_User_CRUD.user.controller;

import com.example.Security_User_CRUD.user.dto.UserRequestDto;
import com.example.Security_User_CRUD.user.service.UserService;
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
