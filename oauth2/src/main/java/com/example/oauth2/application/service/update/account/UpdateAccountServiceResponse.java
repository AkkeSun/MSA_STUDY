package com.example.oauth2.application.service.update.account;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdateAccountServiceResponse {

    private boolean result;

    @Builder
    public UpdateAccountServiceResponse(boolean result) {
        this.result = result;
    }
}
