package com.example.Legacy_Autho.CGI.service;

import com.example.Legacy_Autho.CGI.repository.CGIUserRepository;
import com.example.Legacy_Autho.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CGIUserService {
    private final CGIUserRepository cgiUserRepository;

    public CGIUserService(CGIUserRepository cgiUserRepository) {
        this.cgiUserRepository = cgiUserRepository;
    }

    public String save(User user){
        /*if(cgiUserRepository.findByUsername(user.getUsername()) == null){
            cgiUserRepository.save(user);
            return "add new User";
        } else {
            return "In database";
        }
         */
        cgiUserRepository.save(user);
        return "add new User";
    }

    public User findUser(String username, String password){
        User user = cgiUserRepository.findByUsername(username);
        if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
            return user;
        } else {
            return null;
        }
    }
}