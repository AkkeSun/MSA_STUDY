package com.example.oauth2.application.service.find;

import com.example.oauth2.application.port.in.find.FindAccountUseCase;
import com.example.oauth2.application.port.out.FindAccountPort;
import com.example.oauth2.domain.Account;
import com.example.oauth2.domain.Token;
import com.example.oauth2.infrastructure.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindAccountService implements FindAccountUseCase {

    private final FindAccountPort findAccountPort;

    private final JwtUtils jwtUtils;

    @Override
    public FindAccountServiceResponse findAccount(String accessToken) {
        Token token = jwtUtils.convertToken(accessToken);
        Account account = findAccountPort.findByUserId(token.getUser_name());
        return FindAccountServiceResponse.builder()
            .userId(account.getUserId())
            .name(account.getName())
            .phoneNumber(account.getPhoneNumber())
            .address(account.getAddress())
            .role(account.getRole())
            .lastLoginTime(account.getLastLoginTime())
            .build();
    }
}
