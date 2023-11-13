package com.example.oauth2.adopter.out;

import static com.example.oauth2.infrastructure.exception.ApiErrorCode.USER_INFO_NOT_FOUND;

import com.example.oauth2.application.port.out.AccountCreatePort;
import com.example.oauth2.application.port.out.AccountDeletePort;
import com.example.oauth2.application.port.out.AccountReadPort;
import com.example.oauth2.application.port.out.AccountUpdatePort;
import com.example.oauth2.domain.Account;
import com.example.oauth2.infrastructure.exception.ApiException;
import com.example.oauth2.infrastructure.util.AesUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements AccountCreatePort, AccountReadPort,
    AccountUpdatePort, AccountDeletePort {

    private final AccountRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void create(Account account) {
        AccountEntity entity = AccountEntity.builder()
            .userId(account.getUserId())
            .role(account.getRole())
            .snsSync(account.getSnsSync())
            .name(AesUtils.encrypt(account.getName()))
            .phoneNumber(AesUtils.encrypt(account.getPhoneNumber()))
            .address(AesUtils.encrypt(account.getAddress()))
            .build();
        if(StringUtils.hasText(account.getPassword())){
            entity.setPassword(passwordEncoder.encode(account.getPassword()));
        }
        if(StringUtils.hasText(account.getSnsSecret())){
            entity.setSnsSecret(passwordEncoder.encode(account.getSnsSecret()));
        }
        repository.save(entity);
    }

    @Override
    public boolean existsAccount(String userId) {
        return repository.existsByUserId(userId);
    }

    @Override
    public Account getAccount(String userId) {
        AccountEntity entity = repository.findByUserId(userId).orElseThrow(() ->
            new ApiException(USER_INFO_NOT_FOUND));
        return  Account.builder()
            .userId(entity.getUserId())
            .password(entity.getPassword())
            .snsSync(entity.getSnsSync())
            .snsSecret(entity.getSnsSecret())
            .role(entity.getRole())
            .name(AesUtils.decrypt(entity.getName()))
            .phoneNumber(AesUtils.decrypt(entity.getPhoneNumber()))
            .address(AesUtils.decrypt(entity.getAddress()))
            .build();
    }

    @Override
    @Transactional
    public void update(Account account) {
        AccountEntity entity = repository.findByUserId(account.getUserId()).orElseThrow(() ->
            new ApiException(USER_INFO_NOT_FOUND));
        entity.setRole(account.getRole());
        entity.setSnsSync(account.getSnsSync());
        entity.setName(AesUtils.encrypt(account.getName()));
        entity.setAddress(AesUtils.encrypt(account.getAddress()));
        entity.setPhoneNumber(AesUtils.encrypt(account.getPhoneNumber()));
        if(StringUtils.hasText(account.getPassword())){
            entity.setPassword(passwordEncoder.encode(account.getPassword()));
        }
        if(StringUtils.hasText(account.getSnsSecret())){
            entity.setPassword(passwordEncoder.encode(account.getSnsSecret()));
        }
    }

    @Override
    public void delete(String userId) {
        AccountEntity entity = repository.findByUserId(userId).orElseThrow(() ->
            new ApiException(USER_INFO_NOT_FOUND));
        repository.delete(entity);
    }
}
