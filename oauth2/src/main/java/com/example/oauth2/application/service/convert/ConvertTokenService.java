package com.example.oauth2.application.service.convert;

import com.example.oauth2.application.port.in.convert.ConvertTokenUseCase;
import com.example.oauth2.domain.Token;
import com.example.oauth2.infrastructure.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ConvertTokenService implements ConvertTokenUseCase {

    private final JwtUtils jwtUtils;

    @Override
    public ConvertTokenServiceResponse convertToken(String accessToken) {
        Token token = jwtUtils.convertToken(accessToken);
        return ConvertTokenServiceResponse.builder()
            .exp(token.getExp())
            .user_name(token.getUser_name())
            .authorities(token.getAuthorities())
            .jti(token.getJti())
            .client_id(token.getClient_id())
            .scope(token.getScope())
            .build();
    }
}
