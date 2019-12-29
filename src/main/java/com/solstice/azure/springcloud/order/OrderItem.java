package com.solstice.azure.springcloud.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItem {
    private String id;
    private String productCode;
    private Integer quantity;
    private Double price;
    private Double discountedPrice;
}
