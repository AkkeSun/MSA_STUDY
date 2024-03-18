package com.example.oauth2.application.port.in.register.sns;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterSnsAccountCommand {

    private String userId;

    private String snsSync;

    private String snsSecret;

    private String name;

    private String role;

    private String phoneNumber;

    private String address;

    @Builder
    public RegisterSnsAccountCommand(String userId, String snsSync, String snsSecret, String name,
        String role, String phoneNumber, String address) {
        this.userId = userId;
        this.snsSync = snsSync;
        this.snsSecret = snsSecret;
        this.name = name;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
