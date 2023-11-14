package com.example.oauth2.domain;

import com.example.oauth2.adopter.out.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Account {

    private String userId;
    private String name;
    private String phoneNumber;
    private String address;
    private Role role;
    @JsonIgnore
    private String snsSync;
    @JsonIgnore
    private String snsSecret;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private LocalDateTime lastLoginTime;
}
