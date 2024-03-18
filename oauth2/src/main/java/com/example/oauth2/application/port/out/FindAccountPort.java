package com.example.oauth2.application.port.out;

import com.example.oauth2.domain.Account;

public interface FindAccountPort {

    Account findByUserId(String userId);

    Account findByUserIdAndSnsSync(String userId, String snsSync);

}
