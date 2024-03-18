package com.example.oauth2.adopter.in.update.account;

import com.example.oauth2.application.service.update.account.UpdateAccountServiceResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
class UpdateAccountResponse {

    private boolean result;

    @Builder
    public UpdateAccountResponse(boolean result) {
        this.result = result;
    }

    UpdateAccountResponse of(UpdateAccountServiceResponse response) {
        return UpdateAccountResponse.builder()
            .result(response.isResult())
            .build();
    }
}
