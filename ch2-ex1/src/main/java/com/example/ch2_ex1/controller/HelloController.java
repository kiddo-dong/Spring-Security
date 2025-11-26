package com.example.ch2_ex1.controller;

import com.example.ch2_ex1.ResponseDto;
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

/*
========================================================================

Spring-Security의 기본 동작
항상 인증 + 인가를 가지고 API에 접근해야한다.

인증/인가 X
인증/인가가 없는 API(HTTP 메소드) 호출 -> Status:401(인증 실패)

인증/인가 O
인증/인가 Autho(Header)를 가진 Http메소드 호출 -> Status:200(성공)

Spring Security에서 HTTP Basic 인증을 이용
기본적으로 스프링 시큐리티는 기본 사용자 이름(user)과 App실행 시 제공된 암호를 사용

========================================================================

Spring Security의 자격증명
기본적인 자격 증명의 사용자 이름은 user이며
암호를 UUID로 생성해서 사용

========================================================================

Spring-boot에서의 동작

Spring Context Loading... -> UUID 생성 -> 현재 Application에서 암호를 볼 수 있도록 콘솔에 출력
자격 증명(UUID)을 매모리에 보관.

========================================================================
*/