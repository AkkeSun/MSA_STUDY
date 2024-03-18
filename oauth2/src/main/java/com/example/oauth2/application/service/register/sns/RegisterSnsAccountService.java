package com.example.oauth2.application.service.register.sns;

import static com.example.oauth2.infrastructure.exception.ErrorCode.INVALID_ROLE;

import com.example.oauth2.application.port.in.register.sns.RegisterSnsAccountCommand;
import com.example.oauth2.application.port.in.register.sns.RegisterSnsAccountUseCase;
import com.example.oauth2.application.port.out.CreateAccountPort;
import com.example.oauth2.domain.Role;
import com.example.oauth2.infrastructure.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class RegisterSnsAccountService implements RegisterSnsAccountUseCase {

    private final CreateAccountPort createAccountPort;

    @Override
    public RegisterSnsAccountServiceResponse register(RegisterSnsAccountCommand command) {
        if (Role.getByValue(command.getRole()) == null) {
            throw new CustomException(INVALID_ROLE);
        }

        createAccountPort.registerSnsUser(command);
        return RegisterSnsAccountServiceResponse.builder()
            .result(true)
            .build();
    }
}
