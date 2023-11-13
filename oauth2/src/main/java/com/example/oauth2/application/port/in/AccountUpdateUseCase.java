package com.example.oauth2.application.port.in;

public interface AccountUpdateUseCase {
    AccountResponse update (String accessToken, AccountCommand command);
}
