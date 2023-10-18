package com.example.serviceUser.application.port.out;


import com.example.serviceUser.domain.Account;

public interface AccountCreatePort {
    void create(Account account);
}
