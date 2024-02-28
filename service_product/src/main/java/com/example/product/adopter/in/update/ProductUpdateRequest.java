package com.example.product.adopter.in.update;

import com.example.product.application.port.in.update.ProductUpdateCommand;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
class ProductUpdateRequest {

    @NotNull(message = "상품아이디는 필수값 입니다")
    private Integer productId;

    private String name;

    private String option;

    private Integer categoryId;

    private Integer price;

    private Integer count;

    ProductUpdateCommand toCommand() {
        return ProductUpdateCommand.builder()
            .productId(this.productId)
            .name(this.name)
            .option(this.option)
            .categoryId(this.categoryId)
            .price(this.price)
            .count(this.count)
            .build();
    }
}
