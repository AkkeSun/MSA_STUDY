package com.example.serviceUser.domain;

import com.example.serviceUser.adopter.out.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Account {
    private String username;
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
}
