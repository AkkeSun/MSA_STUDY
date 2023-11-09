package com.example.serviceUser.application.port.out;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenInfo {
    private String exp;
    private String user_name;
    private List<String> authorities;
    private String jti;
    private String client_id;
    private List<String> scope;
}
