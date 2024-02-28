package com.example.product.adopter.in.update;

import com.example.product.application.port.in.update.ProductUpdateUseCase;
import com.example.product.application.service.update.ProductUpdateServiceResponse;
import com.example.product.infrastructure.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
class ProductUpdateController {

    private final ProductUpdateUseCase productUpdateUseCase;

    @PutMapping
    ApiResponse<ProductUpdateResponse> update(@RequestBody ProductUpdateRequest request,
        @RequestHeader(value = "Authorization") String accessToken) {
        ProductUpdateServiceResponse response =
            productUpdateUseCase.update(request.toCommand(), accessToken);
        return ApiResponse.ok(new ProductUpdateResponse().of(response));
    }
}
