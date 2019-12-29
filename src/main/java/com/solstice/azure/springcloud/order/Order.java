package com.solstice.azure.springcloud.order;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Order {
    private List<OrderItem> orderItems;
}
