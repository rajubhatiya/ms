package com.agarment.PaymentService.service;

import com.agarment.PaymentService.model.PaymentRequest;
import com.agarment.PaymentService.model.PaymentResponse;
import org.springframework.http.HttpStatusCode;

public interface PaymentService {
    long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(long orderId);
}
