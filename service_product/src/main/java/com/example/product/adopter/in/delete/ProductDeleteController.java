package com.example.product.adopter.in.delete;

import com.example.product.application.port.in.delete.ProductDeleteUseCase;
import com.example.product.application.service.delete.ProductDeleteServiceResponse;
import com.example.product.infrastructure.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
class ProductDeleteController {

    private final ProductDeleteUseCase productDeleteUseCase;

    @DeleteMapping("/{productId}")
    public ApiResponse<ProductDeleteResponse> delete(@PathVariable("productId") int productId,
        @RequestHeader(value = "Authorization") String accessToken) {
        ProductDeleteServiceResponse response =
            productDeleteUseCase.delete(accessToken, productId);
        return ApiResponse.ok(new ProductDeleteResponse().of(response));
    }
}
