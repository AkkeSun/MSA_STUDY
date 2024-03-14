package com.example.product.adopter.out.persistence.product;

import com.example.product.domain.Product;
import com.example.product.domain.ProductHistory;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "TBL_PRODUCT")
class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TABLE_INDEX")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRODUCT_OPTION")
    private String option;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "PRICE")
    private int price;

    @Column(name = "INVENTORY")
    private int inventory;

    @Column(name = "SELLER")
    private String seller;

    @Column(name = "REG_DATE")
    private LocalDateTime regDate;

    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;

    @Builder
    ProductEntity(Integer id, String name, String option, String category, int price,
        int inventory, String seller, LocalDateTime regDate, LocalDateTime updateDate) {
        this.id = id;
        this.name = name;
        this.option = option;
        this.category = category;
        this.price = price;
        this.inventory = inventory;
        this.seller = seller;
        this.regDate = regDate;
        this.updateDate = updateDate;
    }

    Product toDomain() {
        return Product.builder()
            .id(this.id)
            .name(this.name)
            .option(this.option)
            .category(this.category)
            .price(this.price)
            .inventory(this.inventory)
            .seller(this.seller)
            .regDate(this.regDate)
            .build();
    }

    ProductHistory toHistoryDomain(String description) {
        return ProductHistory.builder()
            .productId(this.id)
            .regDate(this.regDate)
            .description(description)
            .build();
    }

    void updateName(String name) {
        this.name = name;
    }

    void updateOption(String option) {
        this.option = option;
    }

    void updateCategory(String category) {
        this.category = category;
    }

    void updatePrice(int price) {
        this.price = price;
    }

    void updateInventory(int inventory) {
        this.inventory = inventory;
    }

    void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
