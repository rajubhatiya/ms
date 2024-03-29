package com.agarment.OrderService.model;

public enum OrderStatus {
    CREATED,
    PENDING,
    AWAITING_PAYMENT,
    AWAITING_FULFILLMENT,
    AWAITING_SHIPMENT,
    AWAITING_PICKUP,
    PARTIALLY_SHIPPED,
    COMPLETED,
    SHIPPED,
    CANCELLED,
    DECLINED,
    REFUNDED,
    DISPUTED,
    MANUAL_VERIFICATION_REQUIRED,
    PARTIALLY_REFUNDED

}
