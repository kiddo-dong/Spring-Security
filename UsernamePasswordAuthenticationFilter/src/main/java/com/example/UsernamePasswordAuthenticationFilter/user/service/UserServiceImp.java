package com.example.UsernamePasswordAuthenticationFilter.user.service;

import com.example.UsernamePasswordAuthenticationFilter.user.domain.User;
import com.example.UsernamePasswordAuthenticationFilter.user.dto.UserRequestDto;
import com.example.UsernamePasswordAuthenticationFilter.user.dto.UserResponseDto;
import com.example.UsernamePasswordAuthenticationFilter.user.mapper.UserMapper;
import com.example.UsernamePasswordAuthenticationFilter.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImp implements UserService {
    //DI
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImp(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    //create
    @Override
    public String addUser(UserRequestDto userRequestDto){
        User user = userRepository.findByUsername(userRequestDto.getUsername());

        // user가 DB에 없으니 user 생성
        if(user == null){
            User newUser = userMapper.toEntity(userRequestDto);

            userRepository.save(newUser);
        } else {
            throw new UsernameNotFoundException("이미 존재하는 사용자");
        }

        return "addUser";
    }

    //read
    @Override
    public UserResponseDto findUserByUsername(String username){
        User user = userRepository.findByUsername(username);

        if(user == null) throw new UsernameNotFoundException("존재하지 않는 user");

        return userMapper.toResponseDto(user);
    }
}