package com.example.UsernamePasswordAuthenticationFilter.security.login.service;

import com.example.UsernamePasswordAuthenticationFilter.user.domain.User;
import com.example.UsernamePasswordAuthenticationFilter.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        User user = userRepository.findByUsername(username);
        if (user == null) { throw new UsernameNotFoundException(username); }
        return new CustomUserDetails(user);
    }
}
