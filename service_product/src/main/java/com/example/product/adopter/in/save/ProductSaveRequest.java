package com.example.product.adopter.in.save;

import com.example.product.application.port.in.save.ProductSaveCommand;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
class ProductSaveRequest {

    @NotBlank(message = "이름은 필수값 입니다")
    private String name;

    @NotBlank(message = "옵션은 필수값 입니다")
    private String option;

    @NotNull(message = "카테고리 아이디는 필수값 입니다")
    private Integer categoryId;

    @NotNull(message = "상품 금액은 필수값 입니다")
    @Positive(message = "상품 금액은 양수만 가능합니다")
    private Integer price;

    @NotNull(message = "상품 수량은 필수값 입니다")
    @Min(value = 20, message = "상품 수량은 최소 20개 입니다")
    private Integer count;

    ProductSaveCommand toCommand() {
        return ProductSaveCommand.builder()
            .name(this.name)
            .option(this.option)
            .categoryId(this.categoryId)
            .price(this.price)
            .count(this.count)
            .build();
    }
}
