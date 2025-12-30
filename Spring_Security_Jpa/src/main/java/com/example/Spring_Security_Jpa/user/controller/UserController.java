package com.example.Spring_Security_Jpa.user.controller;

import com.example.Spring_Security_Jpa.user.dto.UserInfoResponse;
import com.example.Spring_Security_Jpa.user.dto.UserResponse;
import com.example.Spring_Security_Jpa.user.dto.UserUpdateRequest;
import com.example.Spring_Security_Jpa.user.service.UserAuthService;
import com.example.Spring_Security_Jpa.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET(READ)
    @GetMapping("/me")
    public ResponseEntity<UserInfoResponse> getCurrentUser(@AuthenticationPrincipal String username) {
        UserInfoResponse userInfoResponse = userService.getMyInfo(username);
        return ResponseEntity.ok(userInfoResponse);
    }

    // PUT(UPDATE)
    @PutMapping("/update")
    public ResponseEntity<HttpStatus> userUpdate(
            @AuthenticationPrincipal String username,
            @RequestBody @Validated UserUpdateRequest userUpdateRequest){

        userService.userUpdate(username, userUpdateRequest);

        return ResponseEntity.ok(HttpStatus.OK);
    }


    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteUser(
            @AuthenticationPrincipal String username
    ){
        userService.userDelete(username);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
