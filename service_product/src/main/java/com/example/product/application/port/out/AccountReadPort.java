package com.example.product.application.port.out;

import com.example.product.application.port.in.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="oauth2", url="${oauth2.url}")
public interface AccountReadPort {

    @GetMapping("/oauth/account")
    ProductResponse getAccount(@RequestHeader("Authorization") String accessToken);
}
