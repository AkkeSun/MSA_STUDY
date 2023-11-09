package com.example.serviceUser.application.service;

import static com.example.serviceUser.infrastructure.exception.ApiErrorCode.ACCESS_DENIED;
import static com.example.serviceUser.infrastructure.exception.ApiErrorCode.ALREADY_SAVED_USER;
import static com.example.serviceUser.infrastructure.exception.ApiErrorCode.USER_INFO_NOT_FOUND;

import com.example.serviceUser.adopter.out.Role;
import com.example.serviceUser.application.port.in.AccountCommand;
import com.example.serviceUser.application.port.in.AccountCreateUseCase;
import com.example.serviceUser.application.port.in.AccountDeleteUseCase;
import com.example.serviceUser.application.port.in.AccountResponse;
import com.example.serviceUser.application.port.in.AccountSearchUseCase;
import com.example.serviceUser.application.port.in.AccountUpdateUseCase;
import com.example.serviceUser.application.port.out.AccountCreatePort;
import com.example.serviceUser.application.port.out.AccountDeletePort;
import com.example.serviceUser.application.port.out.AccountReadPort;
import com.example.serviceUser.application.port.out.AccountUpdatePort;
import com.example.serviceUser.application.port.out.TokenReadPort;
import com.example.serviceUser.domain.Account;
import com.example.serviceUser.application.port.out.TokenInfo;
import com.example.serviceUser.infrastructure.exception.ApiException;
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
    private final TokenReadPort tokenReadPort;

    @Override
    public AccountResponse createUser(AccountCommand command) {
        command.validation();
        if(accountReadPort.existsAccount(command.getUsername())){
            throw new ApiException(ALREADY_SAVED_USER);
        }
        accountCreatePort.create(Account.builder()
            .username(command.getUsername())
            .password(command.getPassword())
            .name(command.getName())
            .phoneNumber(command.getPhoneNumber())
            .address(command.getAddress())
            .role(Role.ROLE_USER)
            .build());
        return AccountResponse.ofSuccess(command.getUsername());
    }

    @Override
    public AccountResponse createSnsUser(AccountCommand command) {
        command.snsUserValidation();
        if(accountReadPort.existsAccount(command.getUsername())){
            throw new ApiException(ALREADY_SAVED_USER);
        }
        accountCreatePort.create(Account.builder()
            .username(command.getUsername())
            .name(command.getName())
            .phoneNumber(command.getPhoneNumber())
            .address(command.getAddress())
            .snsSync(command.getSnsSync())
            .snsSecret(command.getSnsSecret())
            .role(Role.ROLE_USER)
            .build());
        return AccountResponse.ofSuccess(command.getUsername());
    }

    @Override
    public AccountResponse getInfo(String accessToken) {
        TokenInfo tokenInfo = tokenReadPort.getTokenInfo("Bearer " + accessToken);
        return AccountResponse.ofSuccess(accountReadPort.getAccount(tokenInfo.getUser_name()));
    }


    @Override
    public AccountResponse update(String accessToken, AccountCommand command) {
        command.updateValidation();
        TokenInfo tokenInfo = tokenReadPort.getTokenInfo(accessToken);
        if(!command.getUsername().equals(tokenInfo.getUser_name())){
            throw new ApiException(ACCESS_DENIED);
        }
        Account account = Account.builder()
            .username(command.getUsername())
            .password(command.getPassword())
            .name(command.getName())
            .phoneNumber(command.getPhoneNumber())
            .address(command.getAddress())
            .role(Role.ROLE_USER)
            .snsSync(command.getSnsSync())
            .snsSecret(command.getSnsSecret())
            .build();
        accountUpdatePort.update(account);
        return AccountResponse.ofSuccess(account.getUsername());
    }

    @Override
    public AccountResponse delete(String accessToken) {
        TokenInfo tokenInfo = tokenReadPort.getTokenInfo(accessToken);
        if(!accountReadPort.existsAccount(tokenInfo.getUser_name())){
            throw new ApiException(USER_INFO_NOT_FOUND);
        }
        accountDeletePort.delete(tokenInfo.getUser_name());
        return AccountResponse.ofSuccess(tokenInfo.getUser_name());
    }
}
