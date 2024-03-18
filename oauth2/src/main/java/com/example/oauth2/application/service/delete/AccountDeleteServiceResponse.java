package com.example.oauth2.application.service.delete;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AccountDeleteServiceResponse {

    private boolean result;

    @Builder
    public AccountDeleteServiceResponse(boolean result) {
        this.result = result;
    }
}
