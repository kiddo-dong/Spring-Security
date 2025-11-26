package com.example.Legacy_Autho.CGI.repository;

import com.example.Legacy_Autho.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/*
인터넷 초기의 사용자/로그인 구현 방식
1. CGI(Common Gateway Interface) 방식
초기 웹 서버에서 동적 페이지를 만들기 위한 방법 -> 매 요청마다 username/password 확인으로 정적 페이지를 동적으로 보이게 만듦

User 객체의 Role Field로 권한 체크
*/

@Repository
public class CGIUserRepository {

    // 가상 DB Map
    private final Map<String , User> userMap = new HashMap<>();

    public String save(User user){
        userMap.put(user.getUsername(), user);
        return "save new User";
    }

    public User findByUsername(String username){
        return userMap.get(username); // HashMap은 Key로 Value를 가져옴 .get() Method
    }

    public String delete(String username){
        User user = findByUsername(username);
        user = null;
        return "removed User";
    }
}