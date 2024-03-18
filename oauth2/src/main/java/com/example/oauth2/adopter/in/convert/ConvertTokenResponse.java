package com.example.oauth2.adopter.in.convert;

import com.example.oauth2.application.service.convert.ConvertTokenServiceResponse;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
class ConvertTokenResponse {

    private String exp;
    private String user_name;
    private List<String> authorities;
    private String jti;
    private String client_id;
    private List<String> scope;

    @Builder
    ConvertTokenResponse(String exp, String user_name, List<String> authorities, String jti,
        String client_id, List<String> scope) {
        this.exp = exp;
        this.user_name = user_name;
        this.authorities = authorities;
        this.jti = jti;
        this.client_id = client_id;
        this.scope = scope;
    }

    ConvertTokenResponse of(ConvertTokenServiceResponse response) {
        return ConvertTokenResponse.builder()
            .exp(response.getExp())
            .user_name(response.getUser_name())
            .authorities(response.getAuthorities())
            .jti(response.getJti())
            .client_id(response.getClient_id())
            .scope(response.getScope())
            .build();
    }


}
