package com.example.oauth2.adopter.in.update.account;

import com.example.oauth2.application.port.in.update.account.UpdateAccountCommand;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
class UpdateAccountRequest {

    private String password;

    private String passwordCheck;

    private String name;

    private String role;

    private String phoneNumber;

    private String address;

    private String snsSync;

    private String snsSecret;

    @Builder
    UpdateAccountRequest(String password, String passwordCheck, String name, String role,
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

    UpdateAccountCommand toCommand() {
        return UpdateAccountCommand.builder()
            .password(password)
            .passwordCheck(passwordCheck)
            .name(name)
            .role(role)
            .phoneNumber(phoneNumber)
            .address(address)
            .snsSync(snsSync)
            .snsSecret(snsSecret)
            .build();
    }
}
