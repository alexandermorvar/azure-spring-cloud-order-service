package com.solstice.azure.springcloud.order;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OrderController {

    private EurekaClient eurekaClient;
    private InventoryServiceClient inventoryServiceClient;

    public OrderController(EurekaClient eurekaClient, InventoryServiceClient inventoryServiceClient) {
        this.eurekaClient = eurekaClient;
        this.inventoryServiceClient = inventoryServiceClient;
    }

    @GetMapping(value = "/eureka-clients", produces = "application/json")
    public ResponseEntity<EurekaClient> getEurekaClients(){
        System.out.println("getting eureka clients");
        return new ResponseEntity<EurekaClient>(eurekaClient, HttpStatus.OK);
    }


    @PostMapping("/order")
    public ResponseEntity<String> submitOrder(@RequestBody Order order) {
        List<Inventory> inventory = createInventoryRequest(order);
        inventoryServiceClient.updateInventory(inventory);
        return new ResponseEntity<String>("Order Submitted Successfully", HttpStatus.OK);
    }

    private List<Inventory> createInventoryRequest(Order order) {
        List<Inventory> inventories = new ArrayList<>();
        order.getOrderItems().forEach(orderItem -> {
            inventories.add(Inventory.builder()
                    .productName(orderItem.getProductCode())
                    .quantity(orderItem.getQuantity())
                    .build());
        });

        return inventories;
    }
}
