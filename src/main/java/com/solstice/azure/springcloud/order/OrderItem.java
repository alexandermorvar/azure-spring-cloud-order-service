package com.solstice.azure.springcloud.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItem {
    private String id;
    private String productName;
    private String productCategory;
    private Integer quantity;
}
