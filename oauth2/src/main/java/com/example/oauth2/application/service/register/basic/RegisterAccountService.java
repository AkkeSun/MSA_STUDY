package com.example.oauth2.application.service.register.basic;

import static com.example.oauth2.infrastructure.exception.ErrorCode.DIFFERENT_PASSWORD_AND_CHECK;
import static com.example.oauth2.infrastructure.exception.ErrorCode.INVALID_ROLE;

import com.example.oauth2.application.port.in.register.basic.RegisterAccountCommand;
import com.example.oauth2.application.port.in.register.basic.RegisterAccountUseCase;
import com.example.oauth2.application.port.out.CreateAccountPort;
import com.example.oauth2.domain.Role;
import com.example.oauth2.infrastructure.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class RegisterAccountService implements RegisterAccountUseCase {

    private final CreateAccountPort createAccountPort;

    @Override
    public RegisterAccountServiceResponse register(RegisterAccountCommand command) {
        if (Role.getByValue(command.getRole()) == null) {
            throw new CustomException(INVALID_ROLE);
        }
        if (!command.getPassword().equals(command.getPasswordCheck())) {
            throw new CustomException(DIFFERENT_PASSWORD_AND_CHECK);
        }

        createAccountPort.register(command);
        return RegisterAccountServiceResponse.builder()
            .result(true)
            .build();
    }
}
