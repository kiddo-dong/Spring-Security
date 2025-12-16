package com.example.Spring_Security_Object.hello.controller;

import com.example.Spring_Security_Object.hello.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public ResponseEntity<ResponseDto> hello(){

        ResponseDto responseDto = new ResponseDto("hello","200");

        return ResponseEntity
                .ok(responseDto);
    }
}