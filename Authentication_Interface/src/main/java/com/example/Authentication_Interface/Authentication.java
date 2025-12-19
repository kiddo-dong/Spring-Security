package com.example.Authentication_Interface;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.security.Principal;
import java.util.Collection;

// Spring-Security
// SecurityContextHolderFilter에서 FilterChain.doFilter 호출시 가장먼저
// AuthenticationFilter로 들어온다.
// AuthenticationFilter를 이해하기 위해
// Authentication <<Interface>>와 구현체를 이해해야한다.


// Client 측에서 넘어온 데이터를 담는 상태 객체
// User 객체
// Token 종류
// Role 등을 담음
public interface Authentication extends Principal, Serializable {

    // 인증
    // 즉, 사용자의 식별자를 얻는 객체
    Object getPrincipal();

    // 자격 증명/증명 수단
    // 사용자를 증명할 증명 수단을 담는 객체이다.
    // JWT/OAuth2/PW/OTP 등등이 존재한다.
    Object getCredentials();

    // 인가 시 사용
    // 권한(ROLE)
    Collection<? extends GrantedAuthority> getAuthorities();

    // 인증이 완료되었는지 안되었는지 판별한다.
    // true : 신뢰적인 사용자 | false : 인증 요청중
    boolean isAuthenticated();

    void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException;
}
/*
Authentication Interface의 구현체
다양한 인증방식이 존재하므로 Authentication 인터페이스의 구현체도 여러가지로 이루어져 있다.

인증방식                |       사용되는 Authentication 구현체
 FormLogin/HTTP Basic | UsernamePasswordAuthenticationToken
     JWT              | JwtAuthenticationToken
     OAth2            | OAuth2AuthenticationToken
     그리고 기타 등등 및 개발자가 원하는 인증 방식으로 직접 구현도 가능하다.
*/