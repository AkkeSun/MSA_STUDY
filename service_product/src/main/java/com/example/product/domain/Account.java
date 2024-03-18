package com.example.product.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Account {

    private String userId;

    private String name;

    private String phoneNumber;

    private String address;

    private String role;

    private LocalDateTime lastLoginTime;

    @Builder
    public Account(String userId, String name, String phoneNumber, String address, String role,
        LocalDateTime lastLoginTime) {
        this.userId = userId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
        this.lastLoginTime = lastLoginTime;
    }
}
