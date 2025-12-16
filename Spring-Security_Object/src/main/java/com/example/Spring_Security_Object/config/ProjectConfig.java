package com.example.Spring_Security_Object.config;

import com.example.Spring_Security_Object.authenticationprovider.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// 설정 class로 Spring에 등록
// 해당 클래스에서 Bean 등록이 가능함
// @Component 와의 차이점
// Component는 인스턴스가 호출마다 인스턴스가 생성될 수 있지만
// Configuration은 인스턴스가 하나로만 생성됨
@Configuration
public class ProjectConfig {

    @Autowired
    private CustomAuthenticationProvider authenticationProvider;

    // 메소드가 반환(return)한 인스턴스를 spring-boot의 Bean으로 등록 할 수 있도록 스프링에 지시
    @Bean
    /*
    UserDetailsService를 빈으로 등록하면
    기본으로 제공되는 Basic Auth를 그대로 쓰지 않고,
    인증에 필요한 ‘사용자 조회 방식’을 내가 정하겠다”
    라고 시큐리티에게 선언
     */

    // UserDetails에서 User를 선언
    public UserDetailsService userDetailsService() {
        var userDetailsService = new InMemoryUserDetailsManager();
        new InMemoryUserDetailsManager();

        // UserDetails 생성
        // 사용자 이름 / 암호 / 권한 목록
        // InMemory User 저장
        var user = User.withUsername("jhon")
                .password("12345")
                .authorities("read")
                .build();
        userDetailsService.createUser(user);

        return userDetailsService;
    }

    // PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // 해쉬 알고리즘이 없기때문에 실무 사용 X
    }

/*
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated() // 모든 요청에 인증이 필요하다.
                )
                .httpBasic();
        return http.build();
    }
*/
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // 인증 없이 요청이 가능하다.
                )
                .httpBasic(httpBasic -> {});
        return http.build();
    }
}