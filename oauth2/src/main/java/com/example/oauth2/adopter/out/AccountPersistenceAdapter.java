package com.example.oauth2.adopter.out;

import static com.example.oauth2.infrastructure.exception.ErrorCode.USER_INFO_NOT_FOUND;

import com.example.oauth2.adopter.out.entity.AccountEntity;
import com.example.oauth2.adopter.out.repository.AccountRepository;
import com.example.oauth2.application.port.in.login.AccountLoginCommand;
import com.example.oauth2.application.port.in.register.AccountRegisterCommand;
import com.example.oauth2.application.port.in.update.AccountUpdateCommand;
import com.example.oauth2.application.port.out.AccountCreatePort;
import com.example.oauth2.application.port.out.AccountDeletePort;
import com.example.oauth2.application.port.out.AccountSearchPort;
import com.example.oauth2.application.port.out.AccountUpdatePort;
import com.example.oauth2.domain.Account;
import com.example.oauth2.domain.Role;
import com.example.oauth2.infrastructure.exception.CustomException;
import com.example.oauth2.infrastructure.util.AesUtils;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
@Component
@Transactional
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements AccountCreatePort, AccountSearchPort,
    AccountUpdatePort, AccountDeletePort {

    private final AccountRepository repository;

    private final PasswordEncoder passwordEncoder;

    public void register(AccountRegisterCommand command) {
        AccountEntity entity = AccountEntity.builder()
            .userId(command.getUserId())
            .password(passwordEncoder.encode(command.getPassword()))
            .role(Role.getByValue(command.getRole()))
            .name(AesUtils.encrypt(command.getName()))
            .phoneNumber(AesUtils.encrypt(command.getPhoneNumber()))
            .regDate(LocalDate.now().toString())
            .address(AesUtils.encrypt(command.getAddress()))
            .build();
        repository.save(entity);
    }

    public void registerSnsUser(AccountRegisterCommand command) {
        AccountEntity entity = AccountEntity.builder()
            .userId(command.getUserId())
            .snsSecret(passwordEncoder.encode(command.getSnsSecret()))
            .role(Role.getByValue(command.getRole()))
            .snsSync(command.getSnsSync())
            .name(AesUtils.encrypt(command.getName()))
            .phoneNumber(AesUtils.encrypt(command.getPhoneNumber()))
            .regDate(LocalDate.now().toString())
            .address(AesUtils.encrypt(command.getAddress()))
            .build();
        repository.save(entity);
    }

    public boolean existsAccount(String userId) {
        return repository.existsByUserId(userId);
    }

    public Account getAccount(String userId) {
        AccountEntity entity = repository.findByUserId(userId).orElseThrow(() ->
            new CustomException(USER_INFO_NOT_FOUND));
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

    public void update(String username, AccountUpdateCommand command) {
        AccountEntity entity = repository.findByUserId(username).orElseThrow(() ->
            new CustomException(USER_INFO_NOT_FOUND));

        if (StringUtils.hasText(command.getRole())) {
            entity.setRole(Role.getByValue(command.getRole()));
        }
        if (StringUtils.hasText(command.getSnsSync())) {
            entity.setSnsSync(command.getSnsSync());
        }
        if (StringUtils.hasText(command.getName())) {
            entity.setName(AesUtils.encrypt(command.getName()));
        }
        if (StringUtils.hasText(command.getAddress())) {
            entity.setAddress(AesUtils.encrypt(command.getAddress()));
        }
        if (StringUtils.hasText(command.getPhoneNumber())) {
            entity.setPhoneNumber(AesUtils.encrypt(command.getPhoneNumber()));
        }
        if (StringUtils.hasText(command.getPassword())) {
            entity.setPassword(passwordEncoder.encode(command.getPassword()));
        }
        if (StringUtils.hasText(command.getSnsSecret())) {
            entity.setPassword(passwordEncoder.encode(command.getSnsSecret()));
        }
    }

    public void updateLoginTime(AccountLoginCommand command) {
        AccountEntity entity = repository.findByUserId(command.getUserId()).orElseThrow(() ->
            new CustomException(USER_INFO_NOT_FOUND));
        entity.setLastLoginTime(command.getLoginTime());
    }

    public void delete(String userId) {
        AccountEntity entity = repository.findByUserId(userId).orElseThrow(() ->
            new CustomException(USER_INFO_NOT_FOUND));
        repository.delete(entity);
    }
}
