package com.example.oauth2.application.service.update.account;

import static com.example.oauth2.infrastructure.exception.ErrorCode.DIFFERENT_PASSWORD_AND_CHECK;

import com.example.oauth2.application.port.in.update.account.UpdateAccountCommand;
import com.example.oauth2.application.port.in.update.account.UpdateAccountUseCase;
import com.example.oauth2.application.port.out.UpdateAccountPort;
import com.example.oauth2.domain.Token;
import com.example.oauth2.infrastructure.exception.CustomException;
import com.example.oauth2.infrastructure.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
class UpdateAccountService implements UpdateAccountUseCase {

    private final UpdateAccountPort updateAccountPort;

    private final JwtUtils jwtUtils;

    @Override
    public UpdateAccountServiceResponse updateAccount(
        String accessToken, UpdateAccountCommand command) {

        Token token = jwtUtils.convertToken(accessToken);

        if (StringUtils.hasText(command.getPassword()) &&
            StringUtils.hasText(command.getPasswordCheck()) &&
            !command.getPassword().equals(command.getPasswordCheck())) {
            throw new CustomException(DIFFERENT_PASSWORD_AND_CHECK);
        }

        updateAccountPort.update(token.getUser_name(), command);

        return UpdateAccountServiceResponse.builder()
            .result(true)
            .build();
    }
}
