package com.example.oauth2.adopter.in.delete;

import com.example.oauth2.application.port.in.delete.AccountDeleteUseCase;
import com.example.oauth2.application.service.delete.AccountDeleteServiceResponse;
import com.example.oauth2.infrastructure.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
class AccountDeleteController {

    private final AccountDeleteUseCase accountDeleteUseCase;

    @DeleteMapping("/oauth/account")
    ApiResponse<AccountDeleteResponse> delete(
        @RequestHeader(value = "Authorization") String accessToken) {
        AccountDeleteServiceResponse response = accountDeleteUseCase.delete(accessToken);
        return ApiResponse.ok(new AccountDeleteResponse().of(response));
    }
}
