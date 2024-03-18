package com.example.oauth2.application.service.update.loginTime;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdateLoginTimeServiceResponse {

    private boolean result;

    @Builder
    public UpdateLoginTimeServiceResponse(boolean result) {
        this.result = result;
    }
}
