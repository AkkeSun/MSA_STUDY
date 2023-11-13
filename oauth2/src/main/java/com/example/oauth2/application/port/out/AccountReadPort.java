package com.example.oauth2.application.port.out;

import com.example.oauth2.domain.Account;

public interface AccountReadPort {

    boolean existsAccount(String userId);

    Account getAccount(String userId);
}
