package com.example.Cookie_Session_ResponseEntity.user.repository;

import com.example.Cookie_Session_ResponseEntity.user.entity.User;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // DB User 조회
    User findByUsername(String username);
}