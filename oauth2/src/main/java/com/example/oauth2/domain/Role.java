package com.example.oauth2.domain;

import java.util.HashMap;
import java.util.Map;

public enum Role {
    ROLE_CUSTOMER, ROLE_SELLER;

    private static final Map<String, Role> roleMap = new HashMap<>();

    static {
        for (Role role : values()) {
            roleMap.put(role.name(), role);
        }
    }

    public static Role getByValue(String value) {
        return roleMap.get(value.toUpperCase());
    }
}