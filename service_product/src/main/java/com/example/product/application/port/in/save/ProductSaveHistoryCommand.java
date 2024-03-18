package com.example.product.application.port.in.save;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductSaveHistoryCommand {

    private Integer productId;
    private LocalDateTime regDate;
    private String description;

    @Builder
    public ProductSaveHistoryCommand(Integer productId, LocalDateTime regDate, String description) {
        this.productId = productId;
        this.regDate = regDate;
        this.description = description;
    }
}
