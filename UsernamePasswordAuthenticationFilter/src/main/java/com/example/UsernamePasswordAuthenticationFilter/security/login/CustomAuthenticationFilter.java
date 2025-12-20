package com.example.UsernamePasswordAuthenticationFilter.security.login;

// 서블릿 레벨 객체
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// 기존의 UsernamePasswordAuthenticationFilter를 상속 받아서 Custom
// 따라서 doFilter()를 직접 사용 안함
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    // 생성자
    // Filter에서 호출 시 AuthenticationManager를 반드시 알아야 함 -> 인증 위임
    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        setAuthenticationManager(authenticationManager);

        // 이 필터가 언제 동작할지 결정
        // /login 경로로 들어온 요청만 이 필터가 처리
        setFilterProcessesUrl("/login"); // 로그인 URL
    }


    // 인증 시도 메소드(핵심 메소드)
    // Username : Password로 인증 시도

    // doFilter() 내부에서 해당 메소드가 호출됨
    // Authentication 객체 리턴
    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response) {

        // Client측 HTTP 요청(request)에서
        // username 추출
        String username = request.getParameter("username");
        // password 추출
        String password = request.getParameter("password");

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

        return this.getAuthenticationManager().authenticate(authRequest);
    }
}

