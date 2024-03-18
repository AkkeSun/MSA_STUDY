package com.example.oauth2.application.service.update.loginTime;

import com.example.oauth2.application.port.in.update.loginTime.UpdateLoginTimeCommand;
import com.example.oauth2.application.port.in.update.loginTime.UpdateLoginTimeUseCase;
import com.example.oauth2.application.port.out.UpdateAccountPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UpdateLoginTimeService implements UpdateLoginTimeUseCase {

    private final UpdateAccountPort updateAccountPort;

    @Override
    public UpdateLoginTimeServiceResponse updateLoginTime(UpdateLoginTimeCommand command) {
        updateAccountPort.updateLoginTime(command);
        
        return UpdateLoginTimeServiceResponse.builder()
            .result(true)
            .build();
    }
}
