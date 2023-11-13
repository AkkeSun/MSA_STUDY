package com.example.oauth2.application.port.out;


import com.example.oauth2.domain.Account;

public interface AccountCreatePort {
    void create(Account account);
}
