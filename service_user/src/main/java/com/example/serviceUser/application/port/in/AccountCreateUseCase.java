package com.example.serviceUser.application.port.in;

public interface AccountCreateUseCase {

    AccountResponse createUser (AccountCommand command);
    AccountResponse createSnsUser (AccountCommand command);
}
