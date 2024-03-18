package com.example.oauth2.application.port.out;


import com.example.oauth2.application.port.in.update.account.UpdateAccountCommand;
import com.example.oauth2.application.port.in.update.loginTime.UpdateLoginTimeCommand;

public interface UpdateAccountPort {

    void update(String username, UpdateAccountCommand command);

    void updateLoginTime(UpdateLoginTimeCommand command);
}
