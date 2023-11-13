package com.example.oauth2.application.port.out;


import com.example.oauth2.domain.Account;

public interface AccountUpdatePort {

    void update(Account account);
}
