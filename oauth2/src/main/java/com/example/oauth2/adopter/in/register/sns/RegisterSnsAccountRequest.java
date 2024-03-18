package com.example.oauth2.adopter.in.register.sns;

import com.example.oauth2.application.port.in.register.sns.RegisterSnsAccountCommand;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
class RegisterSnsAccountRequest {

    @NotBlank(message = "userId 는 필수값 입니다")
    private String userId;

    @NotBlank(message = "name 은 필수값 입니다")
    private String name;

    @NotBlank(message = "role 은 필수값 입니다")
    private String role;

    @NotBlank(message = "phoneNumber 는 필수값 입니다")
    private String phoneNumber;

    @NotBlank(message = "address 는 필수값 입니다")
    private String address;

    @NotBlank(message = "snsSync 는 필수값 입니다")
    private String snsSync;

    @NotBlank(message = "snsSecret 은 필수값 입니다")
    private String snsSecret;

    @Builder
    RegisterSnsAccountRequest(String userId, String name, String role, String phoneNumber,
        String address, String snsSync, String snsSecret) {
        this.userId = userId;
        this.name = name;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.snsSync = snsSync;
        this.snsSecret = snsSecret;
    }

    RegisterSnsAccountCommand toCommand() {
        return RegisterSnsAccountCommand.builder()
            .userId(userId)
            .snsSecret(snsSecret)
            .snsSync(snsSync)
            .name(name)
            .role(role)
            .phoneNumber(phoneNumber)
            .address(address)
            .build();
    }

}
