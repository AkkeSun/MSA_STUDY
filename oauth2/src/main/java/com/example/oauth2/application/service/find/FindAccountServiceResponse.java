package com.example.oauth2.application.service.find;

import com.example.oauth2.domain.Role;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FindAccountServiceResponse {

    private String userId;

    private String name;

    private String phoneNumber;

    private String address;

    private Role role;

    private LocalDateTime lastLoginTime;

    @Builder
    public FindAccountServiceResponse(String userId, String name, String phoneNumber,
        String address,
        Role role, LocalDateTime lastLoginTime) {
        this.userId = userId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
        this.lastLoginTime = lastLoginTime;
    }
}
