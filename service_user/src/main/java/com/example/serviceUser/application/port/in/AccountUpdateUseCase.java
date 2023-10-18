package com.example.serviceUser.application.port.in;

public interface AccountUpdateUseCase {
    AccountResponse update (String accessToken, AccountCommand command);
}
