package com.example.oauth2.application.port.in.register.basic;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterAccountCommand {

    private String userId;

    private String password;

    private String passwordCheck;

    private String name;

    private String role;

    private String phoneNumber;

    private String address;

    @Builder
    public RegisterAccountCommand(String userId, String password, String passwordCheck, String name,
        String role, String phoneNumber, String address) {
        this.userId = userId;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.name = name;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
