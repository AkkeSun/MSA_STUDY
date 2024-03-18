package com.example.oauth2.adopter.in.register.basic;

import com.example.oauth2.application.port.in.register.basic.RegisterAccountUseCase;
import com.example.oauth2.application.service.register.basic.RegisterAccountServiceResponse;
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
class RegisterAccountController {

    private final RegisterAccountUseCase accountRegisterUseCase;

    @PostMapping("/oauth/account")
    ApiResponse<RegisterAccountResponse> accountRegister(
        @RequestBody @Valid RegisterAccountRequest request) {
        RegisterAccountServiceResponse response = accountRegisterUseCase.register(
            request.toCommand());
        return ApiResponse.ok(new RegisterAccountResponse().of(response));
    }

}
