package com.example.oauth2.application.service.delete;

import com.example.oauth2.application.port.in.delete.AccountDeleteUseCase;
import com.example.oauth2.application.port.out.DeleteAccountPort;
import com.example.oauth2.domain.Token;
import com.example.oauth2.infrastructure.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class AccountDeleteService implements AccountDeleteUseCase {

    private final DeleteAccountPort deleteAccountPort;
    private final JwtUtils jwtUtils;

    @Override
    public AccountDeleteServiceResponse delete(String accessToken) {
        Token token = jwtUtils.convertToken(accessToken);
        deleteAccountPort.delete(token.getClient_id());
        return AccountDeleteServiceResponse.builder()
            .result(true)
            .build();
    }
}
