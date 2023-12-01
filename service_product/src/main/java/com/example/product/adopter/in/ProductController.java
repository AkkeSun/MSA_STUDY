package com.example.product.adopter.in;

import com.example.product.application.port.in.ProductCommand;
import com.example.product.application.port.in.ProductCreateUseCase;
import com.example.product.application.port.in.ProductDeleteUseCase;
import com.example.product.application.port.in.ProductResponse;
import com.example.product.application.port.in.ProductSearchUseCase;
import com.example.product.application.port.in.ProductUpdateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductCreateUseCase productCreateUseCase;
    private final ProductUpdateUseCase productUpdateUseCase;
    private final ProductSearchUseCase productSearchUseCase;
    private final ProductDeleteUseCase productDeleteUseCase;

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("productId") int productId) {
        return ResponseEntity.ok(productSearchUseCase.findById(productId));
    }

    @GetMapping("/seller/{seller}")
    public ResponseEntity<ProductResponse> getProductBySeller(@PathVariable("seller") String seller,
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer size) {
        return ResponseEntity.ok(
            productSearchUseCase.findBySeller(seller, PageRequest.of(page, size)));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ProductResponse> getProductByCategory(
        @PathVariable("categoryId") Integer categoryId,
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer size) {
        return ResponseEntity.ok(
            productSearchUseCase.findByCategory(categoryId, PageRequest.of(page, size)));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> save(@RequestBody ProductCommand command,
        @RequestHeader(value = "Authorization") String accessToken) {
        return ResponseEntity.ok(productCreateUseCase.save(command, accessToken));
    }

    @PutMapping
    public ResponseEntity<ProductResponse> update(@RequestBody ProductCommand command,
        @RequestHeader(value = "Authorization") String accessToken) {
        return ResponseEntity.ok(productUpdateUseCase.update(command, accessToken));
    }
 
    @DeleteMapping("/{productId}")
    public ResponseEntity<ProductResponse> delete(@PathVariable("productId") int productId,
        @RequestHeader(value = "Authorization") String accessToken) {
        return ResponseEntity.ok(productDeleteUseCase.delete(accessToken, productId));
    }
}
