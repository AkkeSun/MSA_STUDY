package com.example.product.adopter.in.search;

import com.example.product.application.port.in.search.ProductSearchUseCase;
import com.example.product.application.service.search.ProductSearchServiceResponse;
import com.example.product.infrastructure.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
class ProductSearchController {

    private final ProductSearchUseCase productSearchUseCase;

    @GetMapping("/{productId}")
    ApiResponse<ProductSearchResponse> getProduct(@PathVariable("productId") int productId) {
        ProductSearchServiceResponse response = productSearchUseCase.findById(productId);
        return ApiResponse.ok(new ProductSearchResponse().of(response));
    }

    @GetMapping("/seller/{seller}")
    ApiResponse<ProductSearchResponse> getProductBySeller(@PathVariable("seller") String seller,
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer size) {
        ProductSearchServiceResponse response =
            productSearchUseCase.findBySeller(seller, PageRequest.of(page, size));
        return ApiResponse.ok(new ProductSearchResponse().of(response));
    }

    @GetMapping("/category/{categoryId}")
    ApiResponse<ProductSearchResponse> getProductByCategory(
        @PathVariable("categoryId") Integer categoryId,
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer size) {
        ProductSearchServiceResponse response =
            productSearchUseCase.findByCategory(categoryId, PageRequest.of(page, size));
        return ApiResponse.ok(new ProductSearchResponse().of(response));
    }

}
