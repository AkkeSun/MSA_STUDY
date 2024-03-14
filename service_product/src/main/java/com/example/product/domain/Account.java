package com.example.product.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Account {

    private String userId;

    @Builder
    public Account(String userId) {
        this.userId = userId;
    }
}
