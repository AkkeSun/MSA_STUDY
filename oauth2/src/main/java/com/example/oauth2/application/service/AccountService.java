package com.example.oauth2.application.service;

import static com.example.oauth2.infrastructure.exception.ApiErrorCode.ACCESS_DENIED;
import static com.example.oauth2.infrastructure.exception.ApiErrorCode.ALREADY_SAVED_USER;
import static com.example.oauth2.infrastructure.exception.ApiErrorCode.USER_INFO_NOT_FOUND;

import com.example.oauth2.adopter.out.Role;
import com.example.oauth2.application.port.in.AccountCommand;
import com.example.oauth2.application.port.in.AccountCreateUseCase;
import com.example.oauth2.application.port.in.AccountDeleteUseCase;
import com.example.oauth2.application.port.in.AccountResponse;
import com.example.oauth2.application.port.in.AccountSearchUseCase;
import com.example.oauth2.application.port.in.AccountUpdateUseCase;
import com.example.oauth2.application.port.out.AccountCreatePort;
import com.example.oauth2.application.port.out.AccountDeletePort;
import com.example.oauth2.application.port.out.AccountReadPort;
import com.example.oauth2.application.port.out.AccountUpdatePort;
import com.example.oauth2.domain.Account;
import com.example.oauth2.infrastructure.exception.ApiException;
import com.example.oauth2.infrastructure.util.Token;
import com.example.oauth2.infrastructure.util.TokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService implements AccountCreateUseCase, AccountSearchUseCase,
    AccountUpdateUseCase, AccountDeleteUseCase {

    private final AccountCreatePort accountCreatePort;
    private final AccountReadPort accountReadPort;
    private final AccountUpdatePort accountUpdatePort;
    private final AccountDeletePort accountDeletePort;

    @Override
    public AccountResponse createUser(AccountCommand command) {
        command.validation();
        if (accountReadPort.existsAccount(command.getUserId())) {
            throw new ApiException(ALREADY_SAVED_USER);
        }
        accountCreatePort.create(Account.builder()
            .userId(command.getUserId())
            .password(command.getPassword())
            .name(command.getName())
            .phoneNumber(command.getPhoneNumber())
            .address(command.getAddress())
            .role(Role.getByValue(command.getRole()))
            .build());
        return AccountResponse.ofSuccess(command.getUserId());
    }

    @Override
    public AccountResponse createSnsUser(AccountCommand command) {
        command.snsUserValidation();
        if (accountReadPort.existsAccount(command.getUserId())) {
            throw new ApiException(ALREADY_SAVED_USER);
        }
        accountCreatePort.create(Account.builder()
            .userId(command.getUserId())
            .name(command.getName())
            .phoneNumber(command.getPhoneNumber())
            .address(command.getAddress())
            .snsSync(command.getSnsSync())
            .snsSecret(command.getSnsSecret())
            .role(Role.getByValue(command.getRole()))
            .build());
        return AccountResponse.ofSuccess(command.getUserId());
    }

    @Override
    public AccountResponse getInfo(String accessToken) {
        Token token = TokenUtils.convertToken(accessToken);
        return AccountResponse.ofSuccess(accountReadPort.getAccount(token.getUser_name()));
    }

    @Override
    public AccountResponse update(String accessToken, AccountCommand command) {
        command.updateValidation();
        Token token = TokenUtils.convertToken(accessToken);
        if (!command.getUserId().equals(token.getUser_name())) {
            throw new ApiException(ACCESS_DENIED);
        }
        Account account = Account.builder()
            .userId(command.getUserId())
            .password(command.getPassword())
            .name(command.getName())
            .phoneNumber(command.getPhoneNumber())
            .address(command.getAddress())
            .role(Role.getByValue(command.getRole()))
            .snsSync(command.getSnsSync())
            .snsSecret(command.getSnsSecret())
            .build();
        accountUpdatePort.update(account);
        return AccountResponse.ofSuccess(account.getUserId());
    }

    @Override
    public AccountResponse delete(String accessToken) {
        Token token = TokenUtils.convertToken(accessToken);
        if (!accountReadPort.existsAccount(token.getUser_name())) {
            throw new ApiException(USER_INFO_NOT_FOUND);
        }
        accountDeletePort.delete(token.getUser_name());
        return AccountResponse.ofSuccess(token.getUser_name());
    }
}
