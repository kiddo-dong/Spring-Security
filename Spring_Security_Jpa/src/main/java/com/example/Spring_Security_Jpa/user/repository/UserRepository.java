package com.example.Spring_Security_Jpa.user.repository;

import com.example.Spring_Security_Jpa.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    //CRUD
    User findByUsername(String username);
}