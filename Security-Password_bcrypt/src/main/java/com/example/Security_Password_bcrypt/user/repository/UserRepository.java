package com.example.Security_Password_bcrypt.user.repository;

import com.example.Security_Password_bcrypt.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// CRUD
public interface UserRepository extends JpaRepository<User, Long> {
}
