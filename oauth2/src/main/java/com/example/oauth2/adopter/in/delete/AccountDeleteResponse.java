package com.example.oauth2.adopter.in.delete;

import com.example.oauth2.application.service.delete.AccountDeleteServiceResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
class AccountDeleteResponse {

    private boolean result;

    @Builder
    public AccountDeleteResponse(boolean result) {
        this.result = result;
    }

    AccountDeleteResponse of(AccountDeleteServiceResponse response) {
        return AccountDeleteResponse.builder()
            .result(response.isResult())
            .build();
    }
}
