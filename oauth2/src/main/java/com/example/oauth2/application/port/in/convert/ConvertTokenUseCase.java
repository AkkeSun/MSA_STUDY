package com.example.oauth2.application.port.in.convert;

import com.example.oauth2.application.service.convert.ConvertTokenServiceResponse;

public interface ConvertTokenUseCase {

    ConvertTokenServiceResponse convertToken(String accessToken);
}
