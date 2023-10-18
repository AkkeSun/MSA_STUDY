package com.example.serviceUser.application.port.in;

import static com.example.serviceUser.infrastructure.exception.ApiErrorCode.ADDRESS_IS_NULL;
import static com.example.serviceUser.infrastructure.exception.ApiErrorCode.INVALID_PHONE_NUMBER;
import static com.example.serviceUser.infrastructure.exception.ApiErrorCode.PASSWORD_CHECK_IS_NULL;
import static com.example.serviceUser.infrastructure.exception.ApiErrorCode.PASSWORD_IS_NULL;
import static com.example.serviceUser.infrastructure.exception.ApiErrorCode.PASSWORD_NOT_MATCHING;
import static com.example.serviceUser.infrastructure.exception.ApiErrorCode.PHONE_NUMBER_IS_NULL;
import static com.example.serviceUser.infrastructure.exception.ApiErrorCode.SNS_SECRET_IS_NULL;
import static com.example.serviceUser.infrastructure.exception.ApiErrorCode.SNS_SYNC_IS_NULL;
import static com.example.serviceUser.infrastructure.exception.ApiErrorCode.USERNAME_IS_NULL;

import com.example.serviceUser.infrastructure.exception.ApiException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
public class AccountCommand {
    private String username;
    private String password;
    private String passwordCheck;
    private String name;
    private String phoneNumber;
    private String address;
    private String snsSync;
    private String snsSecret;

    public void validation(){
        if(!StringUtils.hasText(username)){
            throw new ApiException(USERNAME_IS_NULL);
        }
        if(!StringUtils.hasText(password)){
            throw new ApiException(PASSWORD_IS_NULL);
        }
        if(!StringUtils.hasText(passwordCheck)){
            throw new ApiException(PASSWORD_CHECK_IS_NULL);
        }
        if(!StringUtils.hasText(phoneNumber)){
            throw new ApiException(PHONE_NUMBER_IS_NULL);
        }
        if(!StringUtils.hasText(address)){
            throw new ApiException(ADDRESS_IS_NULL);
        }
        if (!password.equals(passwordCheck)){
            throw new ApiException(PASSWORD_NOT_MATCHING);
        }
        if (!phoneNumber.matches("\\d{3}-\\d{3,4}-\\d{4}")){
            throw new ApiException(INVALID_PHONE_NUMBER);
        }
    }

    public void snsUserValidation(){
        if(!StringUtils.hasText(username)){
            throw new ApiException(USERNAME_IS_NULL);
        }
        if(!StringUtils.hasText(phoneNumber)){
            throw new ApiException(PHONE_NUMBER_IS_NULL);
        }
        if(!StringUtils.hasText(snsSync)){
            throw new ApiException(SNS_SYNC_IS_NULL);
        }
        if(!StringUtils.hasText(snsSecret)){
            throw new ApiException(SNS_SECRET_IS_NULL);
        }
        if (!phoneNumber.matches("\\d{3}-\\d{3,4}-\\d{4}")){
            throw new ApiException(INVALID_PHONE_NUMBER);
        }
    }

    public void updateValidation(){
        if(!StringUtils.hasText(username)){
            throw new ApiException(USERNAME_IS_NULL);
        }
        if(!StringUtils.hasText(phoneNumber)){
            throw new ApiException(PHONE_NUMBER_IS_NULL);
        }
        if (!phoneNumber.matches("\\d{3}-\\d{3,4}-\\d{4}")){
            throw new ApiException(INVALID_PHONE_NUMBER);
        }
        if(StringUtils.hasText(snsSync)){
            if(!StringUtils.hasText(snsSecret)){
                throw new ApiException(SNS_SECRET_IS_NULL);
            }
        }
        if(StringUtils.hasText(password)){
            if (!password.equals(passwordCheck)){
                throw new ApiException(PASSWORD_NOT_MATCHING);
            }
        }
    }

}
