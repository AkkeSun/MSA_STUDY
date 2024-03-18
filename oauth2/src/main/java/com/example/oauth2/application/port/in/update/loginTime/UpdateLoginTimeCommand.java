package com.example.oauth2.application.port.in.update.loginTime;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateLoginTimeCommand {

    private String userId;
    private LocalDateTime loginTime;

    @Builder
    public UpdateLoginTimeCommand(String userId, LocalDateTime loginTime) {
        this.userId = userId;
        this.loginTime = loginTime;
    }
}
