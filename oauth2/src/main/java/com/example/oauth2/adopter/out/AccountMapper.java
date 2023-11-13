package com.example.oauth2.adopter.out;

import com.example.oauth2.domain.Account;
import com.example.oauth2.infrastructure.util.AesUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class AccountMapper {

    private final PasswordEncoder passwordEncoder;

    public Account fromEntity (AccountEntity entity) {
        return Account.builder()
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

    public AccountEntity fromDomain (Account account) {
        AccountEntity entity = AccountEntity.builder()
            .userId(account.getUserId())
            .role(account.getRole())
            .snsSync(account.getSnsSync())
            .name(AesUtils.encrypt(account.getName()))
            .phoneNumber(AesUtils.encrypt(account.getPhoneNumber()))
            .address(AesUtils.encrypt(account.getAddress()))
            .snsSecret(account.getSnsSecret())
            .build();
        if(StringUtils.hasText(account.getPassword())){
            entity.setPassword(passwordEncoder.encode(account.getPassword()));
        }
        if(StringUtils.hasText(account.getSnsSecret())){
            entity.setSnsSecret(passwordEncoder.encode(account.getSnsSecret()));
        }
        return entity;
    }

    public AccountEntity updateEntity(AccountEntity entity, Account account) {
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
        return entity;
    }
}
