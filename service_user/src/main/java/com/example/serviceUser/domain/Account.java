package com.example.serviceUser.domain;

import com.example.serviceUser.adopter.out.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Account {
    private String username;
    private String password;
    private String name;
    private String phoneNumber;
    private String address;
    private String snsSync;
    private String snsSecret;
    private Role role;
}
