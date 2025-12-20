package com.example.UsernamePasswordAuthenticationFilter.user.repository;

import com.example.UsernamePasswordAuthenticationFilter.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // CRUD Auto override

    // findByUsername
    User findByUsername(String username);
    // find 시 EntityManager가 PersistenceContext 생성
    // 1차 캐시에 존재 -> 그대로 사용
    // 1차 캐시에 존재 x -> JPA로 DB 검색
}
