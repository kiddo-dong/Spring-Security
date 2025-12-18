package com.example.SecurityContextFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;

// Spring-Security
// SecurityFilterChain에서 가장 먼저 Filter를 거치는 시작 Filter이다.
// 요청을 Wrapper로 감싸 SecurityContext를 생성하고, LocalThread에 넣는다.
// 요청이 끝나면 생명주기를 재거한다.
@Component
public class SecurityContextHolderFilter {
    // DI
    private SecurityContextRepository securityContextRepository;
    private HttpRequestResponseHolder httpRequestResponseHolder;

    public SecurityContextHolderFilter(SecurityContextRepository securityContextRepository, HttpRequestResponseHolder httpRequestResponseHolder) {
        this.securityContextRepository = securityContextRepository;
        this.httpRequestResponseHolder = httpRequestResponseHolder;
    }


    // doFilter Method
    // LocalThread 기반

    // 요청 하나의 생명 주기 시작
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, InterruptedException {
        // 스프링 시큐리티의 Context를 즉시 생성하지 않고
        // 필요하면 생성 할 수 있도록 준비한다. -> Proxy
        // 지연로딩의 구조 (Deferred / Lazy)
        SecurityContext context = securityContextRepository.loadContext(httpRequestResponseHolder);
        // Context를 유지시킨다.
        SecurityContextHolder.setContext(context);

        try {
            // Spring-Security의 FilterChain을 실행시킨다.
            // 요청을 다음 필터로 넘겨준다.
            filterChain.doFilter(request,response);
// 스프링 시큐리티의 Context를 생성한다.        } catch (Exception e) {
            // 예외 처리..?
        } finally {
            // 요청이 끝나면 ContextHolder에서 해당하는 인스턴스의 Context를 remove 한다.
            SecurityContextHolder.clearContext();
        }
    }
}