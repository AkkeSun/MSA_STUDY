package com.example.oauth2.application.port.in;

public interface AccountSearchUseCase {
    AccountResponse getInfo (String accessToken);
}
