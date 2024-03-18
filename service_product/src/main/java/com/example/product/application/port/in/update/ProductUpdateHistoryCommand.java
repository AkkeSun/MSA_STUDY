package com.example.product.application.port.in.update;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductUpdateHistoryCommand {

    private Integer productId;
    private String description;
    private LocalDateTime regDate;

    @Builder
    public ProductUpdateHistoryCommand(Integer productId, String description,
        LocalDateTime regDate) {
        this.productId = productId;
        this.description = description;
        this.regDate = regDate;
    }
}
