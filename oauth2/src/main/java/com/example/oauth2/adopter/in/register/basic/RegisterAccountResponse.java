package com.example.oauth2.adopter.in.register.basic;

import com.example.oauth2.application.service.register.basic.RegisterAccountServiceResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
class RegisterAccountResponse {

    private boolean result;

    @Builder
    RegisterAccountResponse(boolean result) {
        this.result = result;
    }

    RegisterAccountResponse of(RegisterAccountServiceResponse response) {
        return RegisterAccountResponse.builder()
            .result(response.isResult())
            .build();
    }

}
