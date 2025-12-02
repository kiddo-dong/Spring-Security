package com.example.Cookie_Session_ResponseEntity.login.session;

import com.example.Cookie_Session_ResponseEntity.user.entity.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionStore {
    // Session Map 직접 구현
    private static final Map<String, User> sessions = new ConcurrentHashMap<>();

    public static void put(String sessionId, User user){
        sessions.put(sessionId,user);
    }

    public static User get(String sessionId){
        return  sessions.get(sessionId);
    }

    public static void remove(String sessionId){
        sessions.remove(sessionId);
    }

    public static void SessionStatus(){
        System.out.println("=============================================");

        for(Map.Entry<String, User> user : sessions.entrySet()){
            System.out.println("| Key : "+ user.getKey() + " | Value : " + user.getValue()+ " |");
        }

        System.out.println("=============================================");
    }
}