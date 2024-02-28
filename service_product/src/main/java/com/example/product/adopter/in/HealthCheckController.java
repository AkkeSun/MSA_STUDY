package com.example.product.adopter.in;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
class HealthCheckController {

    @GetMapping
    ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("ok");
    }
}
