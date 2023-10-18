package com.example.serviceUser.application.port.out;

import com.example.serviceUser.domain.Account;

public interface AccountReadPort {
    boolean existsAccount (String username);
    Account getAccount (String username);
}
