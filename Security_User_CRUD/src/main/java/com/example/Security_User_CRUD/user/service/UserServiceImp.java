package com.example.Security_User_CRUD.user.service;


import com.example.Security_User_CRUD.user.dto.UserRequestDto;
import com.example.Security_User_CRUD.user.dto.UserResponseDto;
import com.example.Security_User_CRUD.user.mapper.UserMapper;
import com.example.Security_User_CRUD.user.repository.UserRepository;
import com.example.Security_User_CRUD.user.domain.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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