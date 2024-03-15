package com.example.productAgent.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ServerError {

    private String service;

    private String method;

    private int code;

    private String message;

    private LocalDateTime regDate;

    @Builder
    public ServerError(String service, String method, int code, String message,
        LocalDateTime regDate) {
        this.service = service;
        this.method = method;
        this.code = code;
        this.message = message;
        this.regDate = regDate;
    }

}
