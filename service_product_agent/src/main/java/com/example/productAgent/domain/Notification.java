package com.example.productAgent.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Notification {

    private String channel;

    private String userId;

    private String message;

    @Builder
    public Notification(String channel, String userId, String message) {
        this.channel = channel;
        this.userId = userId;
        this.message = message;
    }
}
