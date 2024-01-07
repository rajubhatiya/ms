package com.agarment.OrderService.model;

import lombok.*;

import java.time.Instant;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private long orderId;
    private Instant orderDate;
    private String orderStatus;
    private long amount;
    private ProductDetails productDetails;
    private PaymentDetails paymentDetails;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductDetails {

        private String productName;
        private long productId;
        private long quantity;
        private long price;
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PaymentDetails {

        private String paymentStatus;
        private long paymentId;
        private PaymentMode paymentMode;
        private Instant paymentDate;
    }
}
