package com.example.serviceUser.adopter.out;

import static com.example.serviceUser.infrastructure.exception.ApiErrorCode.USER_INFO_NOT_FOUND;

import com.example.serviceUser.application.port.in.AccountCommand;
import com.example.serviceUser.application.port.out.AccountCreatePort;
import com.example.serviceUser.application.port.out.AccountDeletePort;
import com.example.serviceUser.application.port.out.AccountReadPort;
import com.example.serviceUser.application.port.out.AccountUpdatePort;
import com.example.serviceUser.domain.Account;
import com.example.serviceUser.infrastructure.exception.ApiException;
import com.example.serviceUser.infrastructure.util.AesUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements AccountCreatePort, AccountReadPort,
    AccountUpdatePort, AccountDeletePort {

    private final AccountMapper mapper;
    private final AccountRepository repository;

    @Override
    public void create(Account account) {
        repository.save(mapper.fromDomain(account));
    }

    @Override
    public boolean existsAccount(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public Account getAccount(String username) {
        AccountEntity entity = repository.findByUsername(username);
        if(ObjectUtils.isEmpty(entity)) {
            throw new ApiException(USER_INFO_NOT_FOUND);
        }
        return mapper.fromEntity(entity);
    }

    @Override
    public void update(Account account) {
        AccountEntity entity = repository.findByUsername(account.getUsername());
        if(ObjectUtils.isEmpty(entity)) {
            throw new ApiException(USER_INFO_NOT_FOUND);
        }
        repository.save(mapper.updateEntity(entity, account));
    }

    @Override
    public void delete(String username) {
        AccountEntity entity = repository.findByUsername(username);
        if(ObjectUtils.isEmpty(entity)) {
            throw new ApiException(USER_INFO_NOT_FOUND);
        }
        repository.delete(entity);
    }
}
