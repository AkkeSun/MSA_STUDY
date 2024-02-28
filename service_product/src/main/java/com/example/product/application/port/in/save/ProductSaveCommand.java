package com.example.product.application.port.in.save;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductSaveCommand {

    private Integer productId;

    private String name;

    private String option;

    private Integer categoryId;

    private Integer price;

    private Integer count;

    @Builder
    public ProductSaveCommand(Integer productId, String name, String option, Integer categoryId,
        Integer price, Integer count) {
        this.productId = productId;
        this.name = name;
        this.option = option;
        this.categoryId = categoryId;
        this.price = price;
        this.count = count;
    }
}
