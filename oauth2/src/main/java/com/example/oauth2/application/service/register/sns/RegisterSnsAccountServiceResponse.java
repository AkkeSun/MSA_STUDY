package com.example.oauth2.application.service.register.sns;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RegisterSnsAccountServiceResponse {

    private boolean result;

    @Builder
    public RegisterSnsAccountServiceResponse(boolean result) {
        this.result = result;
    }
}
