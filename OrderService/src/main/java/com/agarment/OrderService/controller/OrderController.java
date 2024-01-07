package com.agarment.OrderService.controller;

import com.agarment.OrderService.model.OrderRequest;
import com.agarment.OrderService.model.OrderResponse;
import com.agarment.OrderService.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/order")
@Log4j2
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     *
     * @param orderRequest
     * @return
     */
    @PreAuthorize("hasAuthority('Customer')")
    @PostMapping("placeOrder")
    public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest){
        long orderId = orderService.placeOrder(orderRequest);
        log.info("Order id {}", orderId);
        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('Admin') || hasAuthority('Customer')")
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable long orderId){
        log.info("Request for order id {}", orderId);
       OrderResponse orderResponse = orderService.getOrderDetails(orderId);
       log.info("ORder respinse value"+ orderResponse.toString());
       return new ResponseEntity<>(orderResponse, HttpStatus.OK);

    }
}
