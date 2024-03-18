package com.example.oauth2.application.port.in.find;

import com.example.oauth2.application.service.find.FindAccountServiceResponse;

public interface FindAccountUseCase {

    FindAccountServiceResponse findAccount(String accessToken);
}
