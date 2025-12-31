package com.example.Spring_Security_Jpa.global.config;

import com.example.Spring_Security_Jpa.global.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Spring에게 요청이 해당 필터체인으로 이루어져야 한다는 것을 명시
    // Spring Security규칙 및 순서 제어
    // 서버 시작 시 한번만 실행
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // SecurityFilterChain 구성 방법 및 규칙
        // 톰캣(서블릿 컨테이너)에서 넘어온 요청을 어떻게 Spring에서 구성 할 것인지 설정

        // Filter + 설정 수집기
        http
                .csrf(csrf -> csrf.disable()) // jwt기반이므로 사용 x | Cookie-Session 기반 시 사용
                .formLogin(form -> form.disable()) // FormLogin 사용 x | username/password 기반 폼 로그인
                .httpBasic(basic -> basic.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/signup").permitAll() // 모든 접근자 허용
                        .requestMatchers("/api/users/login").permitAll()
                        .requestMatchers("/api/users/me").authenticated() // SecurityContext에 Authentication 객체가 존재해야 접근 가능
                        .requestMatchers("/api/users/update").authenticated()
                        .anyRequest().authenticated() // 위의 조건에 다 걸리지 않으면 인증 필요
                ).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // http.build()를 호출하는 순간 Proxy를 실제 수행가능한
        // SecurityFilterChain 객체를 생성
        return http.build();
    }

}
