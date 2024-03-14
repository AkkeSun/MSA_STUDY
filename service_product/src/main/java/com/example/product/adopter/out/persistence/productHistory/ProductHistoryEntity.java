package com.example.product.adopter.out.persistence.productHistory;

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
@Table(name = "TBL_PRODUCT_HISTORY")
class ProductHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TABLE_INDEX")
    private Integer id;

    @Column(name = "PRODUCT_ID")
    private Integer productId;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "REG_DATE")
    private LocalDateTime regDate;

    @Builder
    ProductHistoryEntity(Integer id, Integer productId, String description,
        LocalDateTime regDate) {
        this.id = id;
        this.productId = productId;
        this.description = description;
        this.regDate = regDate;
    }

    ProductHistoryEntity of(ProductHistory history) {
        return ProductHistoryEntity.builder()
            .productId(history.getProductId())
            .regDate(history.getRegDate())
            .description(history.getDescription())
            .build();
    }
}
