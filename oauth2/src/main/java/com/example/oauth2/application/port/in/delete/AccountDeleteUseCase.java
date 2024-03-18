package com.example.oauth2.application.port.in.delete;

import com.example.oauth2.application.service.delete.AccountDeleteServiceResponse;

public interface AccountDeleteUseCase {

    AccountDeleteServiceResponse delete(String accessToken);
}
