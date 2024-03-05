package com.example.oauth2.infrastructure.oauth2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    private final String CLIENT_NAME;

    private final String SECRET;

    private final String JWT_KEY;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final CustomUserDetailService userDetailsService;

    public AuthorizationConfig(AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder, CustomUserDetailService userDetailsService) {
        this.CLIENT_NAME = "oauth2-server";
        this.SECRET = "pass";
        this.JWT_KEY = "oauth2_jwt_key";
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
            .withClient(CLIENT_NAME)
            .secret(passwordEncoder.encode(SECRET))
            .authorizedGrantTypes("password", "refresh_token")
            .scopes("read", "write")
            .redirectUris("http://127.0.0.1:8081")
            .accessTokenValiditySeconds(60 * 5)   // 5분
            .refreshTokenValiditySeconds(60 * 60) // 1시간
            .autoApprove(true); // scope 확인 요청 안함
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
            // .tokenStore(tokenStore)  // jwt 로 변경시 토큰 저장하지 않아도 리소스 서버에서 차제적으로 체크 가능하기 떄문에 필요 없음
            .authenticationManager(authenticationManager)
            .userDetailsService(userDetailsService)
            .accessTokenConverter(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(JWT_KEY);
        return converter;
    }
}
