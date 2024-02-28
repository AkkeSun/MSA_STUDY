package com.example.product.adopter.in.save;

import com.example.product.application.port.in.save.ProductSaveUseCase;
import com.example.product.application.service.save.ProductSaveServiceResponse;
import com.example.product.infrastructure.response.ApiResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
class ProductSaveController {

    private final ProductSaveUseCase saveProductUseCase;

    @PostMapping
    ApiResponse<SaveProductResponse> save(@RequestBody @Valid ProductSaveRequest request,
        @RequestHeader(value = "Authorization") String accessToken) {
        ProductSaveServiceResponse response =
            saveProductUseCase.save(request.toCommand(), accessToken);
        return ApiResponse.ok(new SaveProductResponse().of(response));
    }

}
