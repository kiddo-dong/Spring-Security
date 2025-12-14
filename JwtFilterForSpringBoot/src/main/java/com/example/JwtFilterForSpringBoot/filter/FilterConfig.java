package com.example.JwtFilterForSpringBoot.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Spring-Context에 구성 클래스 추가 및 JwtFilter Bean 등록
@Configuration
public class FilterConfig {

    // Filter Bean 추가
    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter(){
        FilterRegistrationBean<JwtFilter> bean = new FilterRegistrationBean<>();


        bean.setFilter(new JwtFilter());
        bean.addUrlPatterns("/api/*"); // 'api/*' 경로로 들어오는 모든 요청마다 JwtFilter를 항상 거침
        return bean;
    }
}