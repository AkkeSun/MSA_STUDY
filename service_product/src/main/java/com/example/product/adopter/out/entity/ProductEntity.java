package com.example.product.adopter.out.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "TBL_PRODUCT")
public class ProductEntity {

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

    @OneToMany(mappedBy = "productEntity", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<ProductHistoryEntity> productHistoryEntities = new ArrayList<>();

}
