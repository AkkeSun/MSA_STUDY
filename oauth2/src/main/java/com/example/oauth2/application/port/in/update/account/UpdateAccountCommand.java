package com.example.oauth2.application.port.in.update.account;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateAccountCommand {

    private String password;

    private String passwordCheck;

    private String name;

    private String role;

    private String phoneNumber;

    private String address;

    private String snsSync;

    private String snsSecret;

    @Builder
    public UpdateAccountCommand(String password, String passwordCheck, String name, String role,
        String phoneNumber, String address, String snsSync, String snsSecret) {
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.name = name;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.snsSync = snsSync;
        this.snsSecret = snsSecret;
    }
}
