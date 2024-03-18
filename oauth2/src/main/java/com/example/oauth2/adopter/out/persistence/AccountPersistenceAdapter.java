package com.example.oauth2.adopter.out.persistence;

import static com.example.oauth2.infrastructure.exception.ErrorCode.ACCOUNT_NOT_FOUND;

import com.example.oauth2.application.port.in.register.basic.RegisterAccountCommand;
import com.example.oauth2.application.port.in.register.sns.RegisterSnsAccountCommand;
import com.example.oauth2.application.port.in.update.account.UpdateAccountCommand;
import com.example.oauth2.application.port.in.update.loginTime.UpdateLoginTimeCommand;
import com.example.oauth2.application.port.out.CreateAccountPort;
import com.example.oauth2.application.port.out.DeleteAccountPort;
import com.example.oauth2.application.port.out.FindAccountPort;
import com.example.oauth2.application.port.out.UpdateAccountPort;
import com.example.oauth2.domain.Account;
import com.example.oauth2.domain.Role;
import com.example.oauth2.infrastructure.exception.CustomException;
import com.example.oauth2.infrastructure.utils.AesUtils;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
@Component
@Transactional
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements CreateAccountPort, FindAccountPort,
    UpdateAccountPort, DeleteAccountPort {

    private final AccountRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final AesUtils aesUtils;

    public void register(RegisterAccountCommand command) {
        AccountEntity entity = AccountEntity.builder()
            .userId(command.getUserId())
            .password(passwordEncoder.encode(command.getPassword()))
            .role(Role.getByValue(command.getRole()))
            .name(aesUtils.encrypt(command.getName()))
            .phoneNumber(aesUtils.encrypt(command.getPhoneNumber()))
            .regDate(LocalDate.now().toString())
            .address(aesUtils.encrypt(command.getAddress()))
            .build();
        repository.save(entity);
    }

    public void registerSnsUser(RegisterSnsAccountCommand command) {
        AccountEntity entity = AccountEntity.builder()
            .userId(command.getUserId())
            .snsSecret(passwordEncoder.encode(command.getSnsSecret()))
            .role(Role.getByValue(command.getRole()))
            .snsSync(command.getSnsSync())
            .name(aesUtils.encrypt(command.getName()))
            .phoneNumber(aesUtils.encrypt(command.getPhoneNumber()))
            .regDate(LocalDate.now().toString())
            .address(aesUtils.encrypt(command.getAddress()))
            .build();
        repository.save(entity);
    }

    public Account findByUserId(String userId) {
        AccountEntity entity = repository.findByUserId(userId)
            .orElseThrow(() -> new CustomException(ACCOUNT_NOT_FOUND));

        return Account.builder()
            .userId(entity.getUserId())
            .password(entity.getPassword())
            .name(aesUtils.decrypt(entity.getName()))
            .phoneNumber(aesUtils.decrypt(entity.getPhoneNumber()))
            .address(aesUtils.decrypt(entity.getAddress()))
            .snsSync(entity.getSnsSync())
            .snsSecret(entity.getSnsSecret())
            .lastLoginTime(entity.getLastLoginTime())
            .role(entity.getRole())
            .build();
    }

    public Account findByUserIdAndSnsSync(String userId, String snsSync) {
        AccountEntity entity = repository.findByUserIdAndSnsSync(userId, snsSync)
            .orElseThrow(() -> new CustomException(ACCOUNT_NOT_FOUND));

        return Account.builder()
            .userId(entity.getUserId())
            .password(entity.getPassword())
            .name(aesUtils.decrypt(entity.getName()))
            .phoneNumber(aesUtils.decrypt(entity.getPhoneNumber()))
            .address(aesUtils.decrypt(entity.getAddress()))
            .snsSync(entity.getSnsSync())
            .snsSecret(entity.getSnsSecret())
            .lastLoginTime(entity.getLastLoginTime())
            .role(entity.getRole())
            .build();
    }

    public void update(String username, UpdateAccountCommand command) {
        AccountEntity entity = repository.findByUserId(username).orElseThrow(() ->
            new CustomException(ACCOUNT_NOT_FOUND));

        if (StringUtils.hasText(command.getRole())) {
            entity.setRole(Role.getByValue(command.getRole()));
        }
        if (StringUtils.hasText(command.getSnsSync())) {
            entity.setSnsSync(command.getSnsSync());
        }
        if (StringUtils.hasText(command.getName())) {
            entity.setName(aesUtils.encrypt(command.getName()));
        }
        if (StringUtils.hasText(command.getAddress())) {
            entity.setAddress(aesUtils.encrypt(command.getAddress()));
        }
        if (StringUtils.hasText(command.getPhoneNumber())) {
            entity.setPhoneNumber(aesUtils.encrypt(command.getPhoneNumber()));
        }
        if (StringUtils.hasText(command.getPassword())) {
            entity.setPassword(passwordEncoder.encode(command.getPassword()));
        }
        if (StringUtils.hasText(command.getSnsSecret())) {
            entity.setPassword(passwordEncoder.encode(command.getSnsSecret()));
        }
    }

    public void updateLoginTime(UpdateLoginTimeCommand command) {
        AccountEntity entity = repository.findByUserId(command.getUserId()).orElseThrow(() ->
            new CustomException(ACCOUNT_NOT_FOUND));
        entity.setLastLoginTime(command.getLoginTime());
    }

    public void delete(String userId) {
        AccountEntity entity = repository.findByUserId(userId).orElseThrow(() ->
            new CustomException(ACCOUNT_NOT_FOUND));
        repository.delete(entity);
    }
}
