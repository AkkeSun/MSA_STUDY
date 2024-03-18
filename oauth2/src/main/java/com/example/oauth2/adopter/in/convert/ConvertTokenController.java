package com.example.oauth2.adopter.in.convert;

import com.example.oauth2.application.port.in.convert.ConvertTokenUseCase;
import com.example.oauth2.application.service.convert.ConvertTokenServiceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
class ConvertTokenController {

    private final ConvertTokenUseCase convertTokenUseCase;

    @GetMapping(value = "/oauth/account/userinfo")
    public ResponseEntity<ConvertTokenResponse> convertToken(
        @RequestHeader(value = "Authorization") String accessToken) {
        ConvertTokenServiceResponse response = convertTokenUseCase.convertToken(accessToken);
        return ResponseEntity.ok(new ConvertTokenResponse().of(response));
    }

}
