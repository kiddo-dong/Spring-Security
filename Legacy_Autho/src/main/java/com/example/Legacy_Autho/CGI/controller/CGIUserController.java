package com.example.Legacy_Autho.CGI.controller;

import com.example.Legacy_Autho.CGI.service.CGIUserService;
import com.example.Legacy_Autho.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
CGI 방식의 단점
상태성이 없어서 매번 인증/인가를 할때
컨트롤러에서 -> 서비스 <-DB
1.서비스 로직에서 DB와 컨트롤러에서 들어온 요청을 매번 검증해야함
2.로그인 상태 유지 불가
3.보안성 낮음
*/

@RestController
@RequestMapping(name = "/api/cgi")
public class CGIUserController {

    private final CGIUserService cgiUserService;

    public CGIUserController(CGIUserService cgiUserService) {
        this.cgiUserService = cgiUserService;
    }

    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody User user){
        return ResponseEntity
                .ok(cgiUserService.save(user));
    }

    @GetMapping
    public ResponseEntity<User> findUser(@RequestParam String username, @RequestParam String password){
        return ResponseEntity
                .ok(cgiUserService.findUser(username,password));
    }
}
