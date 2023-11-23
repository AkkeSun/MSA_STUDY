package com.example.product.application.port.in;

import static com.example.product.infrastructure.exception.ApiErrorCode.CATEGORY_IS_NULL;
import static com.example.product.infrastructure.exception.ApiErrorCode.COUNT_IS_NULL;
import static com.example.product.infrastructure.exception.ApiErrorCode.INVALID_COUNT;
import static com.example.product.infrastructure.exception.ApiErrorCode.NAME_IS_NULL;
import static com.example.product.infrastructure.exception.ApiErrorCode.OPTION_IS_NULL;
import static com.example.product.infrastructure.exception.ApiErrorCode.PRICE_IS_NULL;
import static com.example.product.infrastructure.exception.ApiErrorCode.PRODUCT_ID_IS_NULL;

import com.example.product.infrastructure.exception.ApiException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
public class ProductCommand {

    private Integer productId;
    private String name;
    private String option;
    private Integer categoryId;
    private Integer price;
    private Integer count;

    public void validation() {
        if (!StringUtils.hasText(name)) {
            throw new ApiException(NAME_IS_NULL);
        }
        if (!StringUtils.hasText(option)) {
            throw new ApiException(OPTION_IS_NULL);
        }
        if (categoryId == null) {
            throw new ApiException(CATEGORY_IS_NULL);
        }
        if (price == null) {
            throw new ApiException(PRICE_IS_NULL);
        }
        if (count == null) {
            throw new ApiException(COUNT_IS_NULL);
        }
        if (count < 20) {
            throw new ApiException(INVALID_COUNT);
        }
    }

    public void updateValidation() {
        if (productId == null) {
            throw new ApiException(PRODUCT_ID_IS_NULL);
        }
    }
}
