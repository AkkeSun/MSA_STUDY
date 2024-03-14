package com.example.product.adopter.out.external.api.account;

import com.example.product.application.port.out.account.AccountSearchPort;
import com.example.product.domain.Account;
import com.example.product.infrastructure.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class AccountExternalApiAdapter implements AccountSearchPort {

    private final AccountFeignClient accountFeignClient;

    @Override
    public String getAccountId(String accessToken) {
        ApiResponse<Account> response = accountFeignClient.getAccount(accessToken);
        return response.getData().getUserId();
    }
}
