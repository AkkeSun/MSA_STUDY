package com.example.serviceUser.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {
    private String status;
    private Object account;

    public static AccountResponse ofSuccess (Object data) {
        return new AccountResponse("Y", data);
    }

}
