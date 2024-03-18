package com.example.oauth2.application.port.out;


import com.example.oauth2.application.port.in.register.sns.RegisterSnsAccountCommand;

public interface CreateAccountPort {

    void register(
        com.example.oauth2.application.port.in.register.basic.RegisterAccountCommand command);

    void registerSnsUser(RegisterSnsAccountCommand command);
}
