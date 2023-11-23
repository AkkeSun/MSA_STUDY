package com.example.product.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private String status;
    private Object data;

    public static ProductResponse ofSuccess(Object data) {
        return new ProductResponse("Y", data);
    }

    public static ProductResponse ofFail(Object data) {
        return new ProductResponse("N", data);
    }

}
