package com.example.oauth2.application.port.out;


import com.example.oauth2.application.port.in.login.AccountLoginCommand;
import com.example.oauth2.application.port.in.update.AccountUpdateCommand;

public interface AccountUpdatePort {

    void update(String username, AccountUpdateCommand command);

    void updateLoginTime(AccountLoginCommand command);
}
