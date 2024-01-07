package com.agarment.PaymentService.repository;

import com.agarment.PaymentService.entity.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDetailsRepository extends JpaRepository<TransactionDetail, Long> {
    TransactionDetail findByorderId(long orderId);
}
