package com.example.oauth2.adopter.in.register.sns;

import com.example.oauth2.application.port.in.register.sns.RegisterSnsAccountUseCase;
import com.example.oauth2.application.service.register.sns.RegisterSnsAccountServiceResponse;
import com.example.oauth2.infrastructure.response.ApiResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
class RegisterSnsAccountController {

    private final RegisterSnsAccountUseCase accountSnsRegisterUseCase;

    @PostMapping("/oauth/account/sns")
    ApiResponse<RegisterSnsAccountResponse> accountSnsRegister(
        @RequestBody @Valid RegisterSnsAccountRequest request) {
        RegisterSnsAccountServiceResponse response = accountSnsRegisterUseCase.register(
            request.toCommand());
        return ApiResponse.ok(new RegisterSnsAccountResponse().of(response));
    }
}
