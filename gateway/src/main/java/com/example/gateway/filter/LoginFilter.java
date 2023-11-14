package com.example.gateway.filter;

import com.example.gateway.filter.LoginFilter.LoginFilterConfig;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class LoginFilter extends AbstractGatewayFilterFactory<LoginFilterConfig> {

    @Value("${oauth2.url}")
    private String oauthUrl;

    public LoginFilter() {
        super(LoginFilterConfig.class);
    }

    @Override
    public GatewayFilter apply(LoginFilterConfig config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            if (request.getPath().toString().equals("/oauth/token")) {

                // 요청 body 를 Mono<DataBuffer>로 변환하여 bodyParam 추출
                return DataBufferUtils.join(request.getBody())
                    .flatMap(dataBuffer -> {
                        String body = StandardCharsets.UTF_8.decode(dataBuffer.asByteBuffer())
                            .toString();
                        MultiValueMap<String, String> bodyParams = parseBodyParams(body);

                        // 요청 body 를 처음 요청 방식으로 재변환한 후 요청
                        ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(request) {
                            @Override
                            public Flux<DataBuffer> getBody() {
                                byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
                                DataBuffer buffer = exchange.getResponse().bufferFactory()
                                    .wrap(bytes);
                                return Flux.just(buffer);
                            }
                        };
                        // post filter
                        return chain.filter(exchange.mutate().request(mutatedRequest).build())
                            .then(Mono.fromRunnable(() -> {
                                if (response.getStatusCode() == HttpStatus.OK &&
                                    Objects.equals(bodyParams.getFirst("grant_type"), "password")) {
                                    // TODO: kafka 로 변경
                                    loginTimeUpdate(bodyParams.getFirst("username"));
                                }
                            }));
                    });
            }
            return chain.filter(exchange);
        };
    }

    private MultiValueMap<String, String> parseBodyParams(String body) {
        MultiValueMap<String, String> bodyParams = new LinkedMultiValueMap<>();
        String[] params = body.split("&");
        for (String param : params) {
            String[] keyValue = param.split("=");
            if (keyValue.length == 2) {
                bodyParams.add(keyValue[0], keyValue[1]);
            }
        }
        return bodyParams;
    }

    private void loginTimeUpdate(String userId) {
        LoginUpdateCommand command = LoginUpdateCommand.builder()
            .userId(userId)
            .lastLoginTime(LocalDateTime.now())
            .build();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LoginUpdateCommand> entity = new HttpEntity<>(
            command, headers);

        restTemplate.exchange(oauthUrl + "/oauth/account/login",
            HttpMethod.PUT, entity, Object.class).getBody();
    }

    @Builder
    @Getter
    private static class LoginUpdateCommand {

        private String userId;
        private LocalDateTime lastLoginTime;
    }

    public static class LoginFilterConfig {

    }
}
