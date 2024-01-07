package com.agarment.OrderService.service;

import com.agarment.OrderService.model.OrderRequest;
import com.agarment.OrderService.model.OrderResponse;

public interface OrderService{
    long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(long orderId);
}
