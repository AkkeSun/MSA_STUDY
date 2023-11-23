package com.example.product.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Product {
    private Integer id;
    private String name;
    private String option;
    private String category;
    private Integer price;
    private Integer inventory;
    private String seller;
}
