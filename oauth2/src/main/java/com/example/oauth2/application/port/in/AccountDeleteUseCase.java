package com.example.oauth2.application.port.in;

public interface AccountDeleteUseCase {
    AccountResponse delete (String accessToken);
}
