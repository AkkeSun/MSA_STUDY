package com.example.oauth2.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Account {

    private String userId;

    private String name;

    private String phoneNumber;

    private String address;

    private Role role;

    private String snsSync;

    private String snsSecret;

    private String password;

    private LocalDateTime lastLoginTime;

    @Builder
    public Account(String userId, String name, String phoneNumber, String address, Role role,
        String snsSync, String snsSecret, String password, LocalDateTime lastLoginTime) {
        this.userId = userId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
        this.snsSync = snsSync;
        this.snsSecret = snsSecret;
        this.password = password;
        this.lastLoginTime = lastLoginTime;
    }
}
