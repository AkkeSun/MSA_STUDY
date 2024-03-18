package com.example.oauth2.application.port.in.register.basic;

import com.example.oauth2.application.service.register.basic.RegisterAccountServiceResponse;

public interface RegisterAccountUseCase {

    RegisterAccountServiceResponse register(RegisterAccountCommand command);
}
