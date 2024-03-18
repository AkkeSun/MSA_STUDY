package com.example.oauth2.adopter.in.register.sns;

import com.example.oauth2.application.service.register.sns.RegisterSnsAccountServiceResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
class RegisterSnsAccountResponse {

    private boolean result;

    @Builder
    RegisterSnsAccountResponse(boolean result) {
        this.result = result;
    }

    RegisterSnsAccountResponse of(RegisterSnsAccountServiceResponse response) {
        return RegisterSnsAccountResponse.builder()
            .result(response.isResult())
            .build();
    }

}
