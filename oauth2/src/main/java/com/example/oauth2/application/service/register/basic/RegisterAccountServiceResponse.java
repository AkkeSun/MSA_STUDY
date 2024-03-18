package com.example.oauth2.application.service.register.basic;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RegisterAccountServiceResponse {

    private boolean result;

    @Builder
    public RegisterAccountServiceResponse(boolean result) {
        this.result = result;
    }
}
