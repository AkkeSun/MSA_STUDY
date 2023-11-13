package com.example.oauth2.application.port.in;

public interface AccountCreateUseCase {

    AccountResponse createUser (AccountCommand command);
    AccountResponse createSnsUser (AccountCommand command);
}
