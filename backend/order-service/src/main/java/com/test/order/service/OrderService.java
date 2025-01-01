package com.test.order.service;

import com.test.order.client.InventoryClient;
import com.test.order.dto.OrderRequest;
import com.test.order.model.Order;
import com.test.order.repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepo orderRepo;

    private final InventoryClient inventoryClient;

    public void placeOrder (OrderRequest orderRequest) {

        var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if(isProductInStock) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setSkuCode(orderRequest.skuCode());
            order.setQuantity(orderRequest.quantity());

            orderRepo.save(order);
        } else{
            throw new RuntimeException("Product with SkuCode" + orderRequest.skuCode() + " is not in stock");
        }


    }
}
