package com.example.serviceUser.application.port.in;

public interface AccountSearchUseCase {
    AccountResponse getInfo (String accessToken);
}
