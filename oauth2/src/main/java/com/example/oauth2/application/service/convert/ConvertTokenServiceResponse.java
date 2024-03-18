package com.example.oauth2.application.service.convert;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ConvertTokenServiceResponse {

    private String exp;
    private String user_name;
    private List<String> authorities;
    private String jti;
    private String client_id;
    private List<String> scope;

    @Builder
    public ConvertTokenServiceResponse(String exp, String user_name, List<String> authorities,
        String jti, String client_id, List<String> scope) {
        this.exp = exp;
        this.user_name = user_name;
        this.authorities = authorities;
        this.jti = jti;
        this.client_id = client_id;
        this.scope = scope;
    }
}
