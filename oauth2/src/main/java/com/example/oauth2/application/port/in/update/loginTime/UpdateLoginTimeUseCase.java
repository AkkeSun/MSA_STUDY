package com.example.oauth2.application.port.in.update.loginTime;

import com.example.oauth2.application.service.update.loginTime.UpdateLoginTimeServiceResponse;

public interface UpdateLoginTimeUseCase {

    UpdateLoginTimeServiceResponse updateLoginTime(UpdateLoginTimeCommand command);
}
