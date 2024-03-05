package com.example.oauth2.application.port.out;


import com.example.oauth2.application.port.in.register.AccountRegisterCommand;

public interface AccountCreatePort {

    void register(AccountRegisterCommand command);

    void registerSnsUser(AccountRegisterCommand command);
}
