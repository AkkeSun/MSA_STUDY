package com.example.oauth2.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {

    private String status;
    private Object data;

    public static AccountResponse ofSuccess(Object data) {
        return new AccountResponse("Y", data);
    }

    public static AccountResponse ofFail(Object data) {
        return new AccountResponse("N", data);
    }
}
