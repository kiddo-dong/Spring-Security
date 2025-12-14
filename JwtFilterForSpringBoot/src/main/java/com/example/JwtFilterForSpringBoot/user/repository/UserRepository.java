package com.example.JwtFilterForSpringBoot.user.repository;

import com.example.JwtFilterForSpringBoot.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // CRUD

    User findByName(String name);
}
