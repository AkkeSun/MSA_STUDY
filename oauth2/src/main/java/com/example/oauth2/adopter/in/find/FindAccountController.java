package com.example.oauth2.adopter.in.find;

import com.example.oauth2.application.port.in.find.FindAccountUseCase;
import com.example.oauth2.application.service.find.FindAccountServiceResponse;
import com.example.oauth2.infrastructure.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
class FindAccountController {

    private final FindAccountUseCase findAccountUseCase;

    @GetMapping("/oauth/account")
    ApiResponse<FindAccountResponse> findAccount(
        @RequestHeader(name = "Authorization") String accessToken) {
        FindAccountServiceResponse response = findAccountUseCase.findAccount(accessToken);
        return ApiResponse.ok(new FindAccountResponse().of(response));
    }

}
