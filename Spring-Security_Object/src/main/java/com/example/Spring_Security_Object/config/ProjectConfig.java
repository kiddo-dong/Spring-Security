package com.example.Spring_Security_Object.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

// 설정 class로 Spring에 등록
// 해당 클래스에서 Bean 등록이 가능함
// @Component 와의 차이점
// Component는 인스턴스가 호출마다 인스턴스가 생성될 수 있지만
// Configuration은 인스턴스가 하나로만 생성됨
@Configuration
public class ProjectConfig {

    // 메소드가 반환(return)한 인스턴스를 spring-boot의 Bean으로 등록 할 수 있도록 스프링에 지시
    @Bean
    /*
    UserDetailsService를 빈으로 등록하면
    기본으로 제공되는 Basic Auth를 그대로 쓰지 않고,
    인증에 필요한 ‘사용자 조회 방식’을 내가 정하겠다”
    라고 시큐리티에게 선언
     */
    public UserDetailsService userDetailsService() {
        var userDetailsService = new InMemoryUserDetailsManager();
        return userDetailsService;
    }
}