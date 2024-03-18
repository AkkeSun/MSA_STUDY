package com.example.oauth2.adopter.in.update.account;

import com.example.oauth2.application.port.in.update.account.UpdateAccountUseCase;
import com.example.oauth2.application.service.update.account.UpdateAccountServiceResponse;
import com.example.oauth2.infrastructure.response.ApiResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
class UpdateAccountController {

    private final UpdateAccountUseCase updateAccountUseCase;

    @PutMapping("/oauth/account")
    ApiResponse<UpdateAccountResponse> updateAccount(
        @RequestHeader(value = "Authorization") String accessToken,
        @RequestBody @Valid UpdateAccountRequest request) {
        UpdateAccountServiceResponse response = updateAccountUseCase.updateAccount(
            accessToken, request.toCommand());
        return ApiResponse.ok(new UpdateAccountResponse().of(response));
    }
}
