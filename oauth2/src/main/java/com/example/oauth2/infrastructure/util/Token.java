package com.example.oauth2.infrastructure.util;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Token {

    private String exp;
    private String user_name;
    private List<String> authorities;
    private String jti;
    private String client_id;
    private List<String> scope;
}