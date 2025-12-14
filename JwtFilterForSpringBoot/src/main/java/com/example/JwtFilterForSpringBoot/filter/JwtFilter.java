package com.example.JwtFilterForSpringBoot.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


// Servlet에서 동작하는 Filter의 인증 / 인가 흐름
public class JwtFilter implements Filter {

    // 클라이언트 측 요청으로 HTTP/HTTPS request 받음
    // response 형식 지정해줌
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // JWT는 인증된 사용자를 기반으로 동작함
        // 즉 서버가 인증성공 시 jwt를 발급해서 클라이언트에게 응답해줌

        // 강제 형변환으로 객체 생성 및 대입
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String path = req.getRequestURI();

        // 로그인 요청이면 필터 패스
        if (path.equals("/api/auth/login")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // HTTP 요청의 Header에서 Header의 Authorization키의 value를 가져옴
        // 즉, Authorization의 value를 가져옴
        // // Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...

        // HTTP 요청에는 여러 헤더(메타데이터)가 존재함
        // 그 중 인증관련 헤더가 Authorization
        // Basic Auth -> Authorization: Basic dXNlcjpwYXNz
        // Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
        // Authorization: Bearer <access_token>
        String authHeader = req.getHeader("Authorization");

        // Authorization이 null 또는 토큰 인증의 시작점인 Bearer가 존재하지 않으면 인가 X
        // JWT 토큰이 존재하는지 안하는지 검증
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 4xx 상태코드 전송
            return; // 컨트롤러까지 안보냄
        }

        // "Bearer "를 문자열에서 제거 - 문자열 앞에서 7개 제거(인덱스 0-6)
        // 즉, JWT를 Header.Payload.Signature로 나누기 위해서 문자열 파싱
        // 해당 로직에서 딱 JWT 토큰만 남김
        String token = authHeader.substring(7);

        //JWT 토큰 검증 유틸 호출 후 검증
        // Token이 인증 실패시 return
        if(!JwtUtil.validate(token)){
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 검증 완료 시 -> 컨트롤러로 위임
        filterChain.doFilter(servletRequest, servletResponse);
    }
}