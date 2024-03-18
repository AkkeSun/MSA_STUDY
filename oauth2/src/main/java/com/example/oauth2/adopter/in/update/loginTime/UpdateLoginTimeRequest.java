package com.example.oauth2.adopter.in.update.loginTime;

import com.example.oauth2.application.port.in.update.loginTime.UpdateLoginTimeCommand;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
class UpdateLoginTimeRequest {

    @NotBlank(message = "userId 는 필수값 입니다")
    private String userId;

    @NotNull(message = "loginTime 는 필수값 입니다")
    private LocalDateTime loginTime;

    UpdateLoginTimeCommand toCommand() {
        return UpdateLoginTimeCommand.builder()
            .userId(userId)
            .loginTime(loginTime)
            .build();
    }
}
