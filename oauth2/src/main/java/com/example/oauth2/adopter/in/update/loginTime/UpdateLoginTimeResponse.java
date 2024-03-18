package com.example.oauth2.adopter.in.update.loginTime;

import com.example.oauth2.application.service.update.loginTime.UpdateLoginTimeServiceResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
class UpdateLoginTimeResponse {

    private boolean result;

    @Builder
    UpdateLoginTimeResponse(boolean result) {
        this.result = result;
    }

    UpdateLoginTimeResponse of(UpdateLoginTimeServiceResponse response) {
        return UpdateLoginTimeResponse.builder()
            .result(response.isResult())
            .build();
    }
}
