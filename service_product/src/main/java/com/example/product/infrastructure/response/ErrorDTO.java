package com.example.product.infrastructure.response;

import com.example.product.infrastructure.exception.CustomException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindException;

@Getter
@NoArgsConstructor
public class ErrorDTO {

    private String errorCode;

    private String errorMsg;

    @Builder
    public ErrorDTO(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ErrorDTO of(BindException e) {
        return ErrorDTO.builder()
            .errorCode(e.getBindingResult().getAllErrors().get(0).getCode())
            .errorMsg(e.getBindingResult().getAllErrors().get(0).getDefaultMessage())
            .build();
    }

    public ErrorDTO of(CustomException e) {
        return ErrorDTO.builder()
            .errorCode(e.getErrorCode().getCode())
            .errorMsg(e.getErrorCode().getMessage())
            .build();
    }

    public ErrorDTO of(Exception e) {
        return ErrorDTO.builder()
            .errorCode("500")
            .errorMsg(e.getMessage())
            .build();
    }
}
