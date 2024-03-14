package com.example.product.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductHistory {

    private Integer productId;

    private LocalDateTime regDate;

    private String description;

    @Builder
    public ProductHistory(Integer productId, LocalDateTime regDate, String description) {
        this.productId = productId;
        this.regDate = regDate;
        this.description = description;
    }
}
