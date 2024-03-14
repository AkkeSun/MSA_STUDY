package com.example.product.application.port.in.delete;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductDeleteHistoryCommand {

    private Integer productId;
    private LocalDateTime regDate;
    private String description;

    @Builder
    public ProductDeleteHistoryCommand(Integer productId, LocalDateTime regDate,
        String description) {
        this.productId = productId;
        this.regDate = regDate;
        this.description = description;
    }
}
