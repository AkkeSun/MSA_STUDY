package com.example.oauth2.application.port.in.update.account;

import com.example.oauth2.application.service.update.account.UpdateAccountServiceResponse;

public interface UpdateAccountUseCase {

    UpdateAccountServiceResponse updateAccount(String accessToken,
        UpdateAccountCommand command);
}
