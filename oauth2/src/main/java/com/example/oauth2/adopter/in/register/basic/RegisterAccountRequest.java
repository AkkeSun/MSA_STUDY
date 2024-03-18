package com.example.oauth2.adopter.in.register.basic;

import com.example.oauth2.application.port.in.register.basic.RegisterAccountCommand;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
class RegisterAccountRequest {

    @NotBlank(message = "userId 는 필수값 입니다")
    private String userId;

    @NotBlank(message = "password 는 필수값 입니다")
    private String password;

    @NotBlank(message = "passwordCheck 는 필수값 입니다")
    private String passwordCheck;

    @NotBlank(message = "name 은 필수값 입니다")
    private String name;

    @NotBlank(message = "role 은 필수값 입니다")
    private String role;

    @NotBlank(message = "phoneNumber 는 필수값 입니다")
    private String phoneNumber;

    @NotBlank(message = "address 는 필수값 입니다")
    private String address;

    @Builder
    RegisterAccountRequest(String userId, String password, String passwordCheck, String name,
        String role, String phoneNumber, String address) {
        this.userId = userId;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.name = name;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    RegisterAccountCommand toCommand() {
        return RegisterAccountCommand.builder()
            .userId(userId)
            .password(password)
            .passwordCheck(passwordCheck)
            .name(name)
            .role(role)
            .phoneNumber(phoneNumber)
            .address(address)
            .build();
    }

}
