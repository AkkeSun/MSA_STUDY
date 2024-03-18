package com.example.oauth2.adopter.in.update.loginTime;

import com.example.oauth2.application.port.in.update.loginTime.UpdateLoginTimeUseCase;
import com.example.oauth2.application.service.update.loginTime.UpdateLoginTimeServiceResponse;
import com.example.oauth2.infrastructure.response.ApiResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
class UpdateLoginTimeController {

    private final UpdateLoginTimeUseCase updateLoginTimeUseCase;

    @PutMapping("/oauth/account/loginTime")
    ApiResponse<UpdateLoginTimeResponse> updateLoginTime(
        @RequestBody @Valid UpdateLoginTimeRequest request) {
        UpdateLoginTimeServiceResponse response = updateLoginTimeUseCase.updateLoginTime(
            request.toCommand());
        return ApiResponse.ok(new UpdateLoginTimeResponse().of(response));
    }
}
