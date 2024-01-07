package com.agarment.PaymentService.service;

import com.agarment.PaymentService.entity.TransactionDetail;
import com.agarment.PaymentService.model.PaymentMode;
import com.agarment.PaymentService.model.PaymentRequest;
import com.agarment.PaymentService.model.PaymentResponse;
import com.agarment.PaymentService.repository.TransactionDetailsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;
    @Override
    public long doPayment(PaymentRequest paymentRequest) {
        log.info("Recording payment details: {} ", paymentRequest);
        TransactionDetail transactionDetail = TransactionDetail.builder()
                .amount(paymentRequest.getAmount())
                .paymentMode(paymentRequest.getPaymentMode().name())
                .paymentStatus("SUCCESS")
                .orderId(paymentRequest.getOrderId())
                .referenceNumber(paymentRequest.getReferenceNumber())
                .paymentDate(Instant.now())
                .build();
        transactionDetailsRepository.save(transactionDetail);
        log.info("Transaction completed with id {} ", transactionDetail.getId());
        return transactionDetail.getId();
    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(long orderId) {
        log.info("payment details for order id {}"+orderId);
        TransactionDetail transactionDetails = transactionDetailsRepository.findByorderId(orderId);

        PaymentResponse paymentResponse
                = PaymentResponse.builder()
                .paymentId(transactionDetails.getId())
                .paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
                .paymentDate(transactionDetails.getPaymentDate())
                .orderId(transactionDetails.getOrderId())
                .status(transactionDetails.getPaymentStatus())
                .amount(transactionDetails.getAmount())
                .build();

        return paymentResponse;
    }
}
