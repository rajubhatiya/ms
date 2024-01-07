package com.agarment.OrderService.service;

import com.agarment.OrderService.entity.Order;
import com.agarment.OrderService.exception.CustomException;
import com.agarment.OrderService.external.client.PaymentService;
import com.agarment.OrderService.external.client.ProductService;
import com.agarment.OrderService.external.request.PaymentRequest;
import com.agarment.OrderService.external.response.PaymentResponse;
import com.agarment.OrderService.model.*;
import com.agarment.OrderService.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
@Service
@Log4j2
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private RestTemplate restTemplate;
    /**
     *
     * @param orderRequest
     * @return
     */
    @Override
    public long placeOrder(OrderRequest orderRequest) {
        //Order Entity -> Save the data with Status Order Created
        //Product Service - Block Products (Reduce the Quantity)
        //Payment Service -> Payments -> Success-> COMPLETE, Else
        //CANCELLED

        log.info("Placing Order Request: {}", orderRequest);

        productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuanity());
        log.info("Order creating with status CREATED...");
        Order order = Order.builder()
                .productId(orderRequest.getProductId())
                .quanity(orderRequest.getQuanity())
                .amount(orderRequest.getTotalAmount())
                .orderDate(Instant.now())
                .orderStatus(OrderStatus.CREATED.name().toString())
                .build();
        order = orderRepository.save(order);
        log.info("Order place successfully with order id {}", order.getId());

        log.info("Calling Payment Service to complete the payment....");
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .orderId(order.getId())
                .paymentMode(orderRequest.getPaymentMode())
                .amount(orderRequest.getTotalAmount())
                .build();

        String orderStatus=null;
        try {
            paymentService.doPayment(paymentRequest);
            log.info("Payment done successfully. Changing order status to PLACED");
            orderStatus = "PLACED";
        }catch (Exception e){
            e.printStackTrace();
            log.info("Payment has need failed. Changing order status to PAYMENT_FAILED");
            orderStatus = "PAYMENT_FAILED";
        }
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
        return order.getId();
    }

    @Override
    public OrderResponse getOrderDetails(long orderId) {
        log.info("Order details for order id {}", orderId);
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new CustomException("Order not found for give id","ORDER_NOT_FOUND",404));
log.info("Gettting payent information for order id {}", orderId);
        PaymentResponse paymentResponse =  restTemplate.getForObject(
                "http://PAYMENT-SERVICE/v1/payment/order/"+orderId
                ,PaymentResponse.class
        );
log.info("Collecting details for product response....");
        ProductResponse  productResponse = restTemplate.getForObject("http://PRODUCT-SERVICE/v1/product/"+order.getProductId(), ProductResponse.class);
        OrderResponse.PaymentDetails paymentDetails = OrderResponse.PaymentDetails.builder()
                .paymentId(paymentResponse.getPaymentId())
                .paymentDate(paymentResponse.getPaymentDate())
                .paymentMode(paymentResponse.getPaymentMode())
                .paymentStatus(paymentResponse.getStatus())
                .build();
log.info("Collecting details for product");
        OrderResponse.ProductDetails productDetails = OrderResponse.ProductDetails.builder()
                .productId(productResponse.getProductId())
                .productName(productResponse.getProductName())
                .price(productResponse.getPrice())
                .build();
        log.info("Collecting details for order response....");

        OrderResponse orderResponse = OrderResponse.builder()
                .orderId(orderId)
                .orderStatus(order.getOrderStatus())
                .amount(order.getAmount())
                .orderDate(order.getOrderDate())
                .productDetails(productDetails)
                .paymentDetails(paymentDetails)
                .build();
        return orderResponse;
    }
}
