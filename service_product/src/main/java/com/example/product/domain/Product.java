package com.example.product.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Product {

    private Integer id;

    private String name;

    private String option;

    private String category;

    private int price;

    private Integer inventory;

    private String seller;

    private LocalDateTime regDate;

    @Builder
    public Product(Integer id, String name, String option, String category, int price,
        Integer inventory, String seller, LocalDateTime regDate) {
        this.id = id;
        this.name = name;
        this.option = option;
        this.category = category;
        this.price = price;
        this.inventory = inventory;
        this.seller = seller;
        this.regDate = regDate;
    }
}
