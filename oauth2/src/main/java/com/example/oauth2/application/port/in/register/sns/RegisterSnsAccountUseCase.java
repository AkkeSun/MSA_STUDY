package com.example.oauth2.application.port.in.register.sns;

import com.example.oauth2.application.service.register.sns.RegisterSnsAccountServiceResponse;

public interface RegisterSnsAccountUseCase {

    RegisterSnsAccountServiceResponse register(RegisterSnsAccountCommand command);
}
