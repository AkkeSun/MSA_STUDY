package com.example.oauth2.adopter.in.find;

import com.example.oauth2.application.service.find.FindAccountServiceResponse;
import com.example.oauth2.domain.Role;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
class FindAccountResponse {

    private String userId;

    private String name;

    private String phoneNumber;

    private String address;

    private Role role;

    private LocalDateTime lastLoginTime;

    @Builder
    public FindAccountResponse(String userId, String name, String phoneNumber, String address,
        Role role, LocalDateTime lastLoginTime) {
        this.userId = userId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
        this.lastLoginTime = lastLoginTime;
    }

    FindAccountResponse of(FindAccountServiceResponse response) {
        return FindAccountResponse.builder()
            .userId(response.getUserId())
            .name(response.getName())
            .phoneNumber(response.getPhoneNumber())
            .address(response.getAddress())
            .role(response.getRole())
            .lastLoginTime(response.getLastLoginTime())
            .build();
    }

}
