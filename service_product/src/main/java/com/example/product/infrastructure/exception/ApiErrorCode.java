package com.example.product.infrastructure.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ApiErrorCode {

    PRODUCT_ID_IS_NULL("400", "상품 아이디를 입력하지 않았습니다"),
    NAME_IS_NULL("400", "상품명을 입력하지 않았습니다"),
    OPTION_IS_NULL("400", "상품 옵션을 입력하지 않았습니다"),
    CATEGORY_IS_NULL("400", "상품 카테고리를 입력하지 않았습니다"),
    PRICE_IS_NULL("400", "상품 금액을 입력하지 않았습니다"),
    COUNT_IS_NULL("400", "수량을 입력하지 않았습니다"),
    INVALID_CATEGORY("400", "유효하지 않은 카테고리 입니다"),
    INVALID_COUNT ("400", "최소 수량을 입력하지 않았습니다"),
    INVALID_PRODUCT_CODE ("400", "유효하지 않는 상품코드 입니다"),
    INVALID_UPDATE_ITEM ("400", "업데이트 항목이 없습니다"),
    ACCESS_DENIED ("401", "권한이 없습니다");

    private final String errorCode;
    private final String message;

    public String errorCode() {
        return this.errorCode;
    }

    public String message() {
        return this.message;
    }

}
