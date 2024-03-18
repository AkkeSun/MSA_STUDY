package com.example.product.adopter.out.persistence.category;

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
@Table(name = "TBL_PRODUCT_CATEGORY")
class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TABLE_INDEX")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Builder
    CategoryEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
