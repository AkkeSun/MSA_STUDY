package com.example.product.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Product {
    private Integer id;
    private String name;
    private String option;
    private String category;
    private Integer price;
    private Integer inventory;
    private String seller;

    @Builder
    public Product(Integer id, String name, String option, String category, Integer price,
        Integer inventory, String seller) {
        this.id = id;
        this.name = name;
        this.option = option;
        this.category = category;
        this.price = price;
        this.inventory = inventory;
        this.seller = seller;
    }
}
