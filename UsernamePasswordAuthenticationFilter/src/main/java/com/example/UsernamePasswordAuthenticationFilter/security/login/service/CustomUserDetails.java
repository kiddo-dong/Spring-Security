package com.example.UsernamePasswordAuthenticationFilter.security.login.service;

import com.example.UsernamePasswordAuthenticationFilter.user.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    // 🔥 권한 (인가 단계에서 사용)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 아직 인가 안 쓸 거면 빈 리스트로 둬도 됨
        return List.of();
    }

    // 🔥 DB에 저장된 비밀번호
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    // 🔥 로그인 ID
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 아래는 일단 true 고정해도 됨
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // 👉 필요하면 도메인 User 꺼내 쓰기
    public User getUser() {
        return user;
    }
}
