package com.example.Legacy_Autho.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;

public class User {

    private String username;
    private String password;
    private String role; // "USER" 또는 "ADMIN"

    public User(){}

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role; // 권한 체크 | USER or ADMIN
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}