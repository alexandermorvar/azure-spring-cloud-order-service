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
    public ResponseEntity<String> submitOrder(@RequestBody List<Order> orders) {
        List<Inventory> inventory = createInventoryRequest(orders);
        inventoryServiceClient.updateInventory(inventory);
        return new ResponseEntity<String>("Order Submitted Successfully",
                HttpStatus.OK);
    }

    private List<Inventory> createInventoryRequest(List<Order> orders) {
        List<Inventory> inventories = new ArrayList<>();
        orders.forEach(order -> {
            inventories.add(Inventory.builder()
                    .id(order.getInventoryId())
                    .productName(order.getProductName())
                    .productCategory(order.getProductCategory())
                    .quantity(order.getQuantity())
                    .build());
        });

        return inventories;
    }
}
