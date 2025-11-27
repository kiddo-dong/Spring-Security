package com.example.Legacy_Autho.CGI.repository;

import com.example.Legacy_Autho.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/*
인터넷 초기의 사용자/로그인 구현 방식
1. CGI(Common Gateway Interface) 방식
초기 웹 서버에서 동적 페이지를 만들기 위한 방법 -> 매 요청마다 username/password 확인으로 정적 페이지를 동적으로 보이게 만듦

User 객체의 Role Field로 권한 체크
*/

@Repository
public class CGIUserRepository {

    private final Logger logger = Logger.getLogger(CGIUserRepository.class.getName());

    // 가상 DB Map
    private final Map<String , User> CGIuserDB = new HashMap<>();

    public void save(User user){
        CGIuserDB.put(user.getUsername(), user);
        // DB 상태 출력
        for(Map.Entry<String, User> userEntry : CGIuserDB.entrySet()){
            logger.info(userEntry.getKey() + " : " + userEntry.getValue());
        }
    }

    public User findByUsername(String username){
        return CGIuserDB.get(username); // DB에서 user를 get
    }

    public void delete(String username){
        User user = findByUsername(username);
        user = null;
    }
}