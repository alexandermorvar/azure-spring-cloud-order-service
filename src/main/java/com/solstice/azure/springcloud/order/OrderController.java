package com.solstice.azure.springcloud.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {

    private InventoryServiceClient inventoryServiceClient;

    public OrderController(InventoryServiceClient inventoryServiceClient) {
        this.inventoryServiceClient = inventoryServiceClient;
    }

    @PostMapping("/order")
    public ResponseEntity<String> submitOrder(@RequestBody List<OrderItem> orderItems) {
        List<Inventory> inventory = createInventoryRequest(orderItems);
        inventoryServiceClient.updateInventory(inventory);
        return new ResponseEntity<String>("Order Submitted Successfully",
                HttpStatus.OK);
    }

    private List<Inventory> createInventoryRequest(List<OrderItem>  orderItems) {
        List<Inventory> inventories = new ArrayList<>();
        orderItems.forEach(orderItem -> {
            inventories.add(Inventory.builder()
                    .id(orderItem.getId())
                    .productName(orderItem.getProductName())
                    .productCategory(orderItem.getProductCategory())
                    .quantity(orderItem.getQuantity())
                    .build());
        });

        return inventories;
    }
}
