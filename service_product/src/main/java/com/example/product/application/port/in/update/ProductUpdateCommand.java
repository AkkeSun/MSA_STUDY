package com.example.product.application.port.in.update;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductUpdateCommand {

    private Integer productId;

    private String name;

    private String option;

    private Integer categoryId;

    private Integer price;

    private Integer count;

    @Builder
    public ProductUpdateCommand(Integer productId, String name, String option, Integer categoryId,
        Integer price, Integer count) {
        this.productId = productId;
        this.name = name;
        this.option = option;
        this.categoryId = categoryId;
        this.price = price;
        this.count = count;
    }
}