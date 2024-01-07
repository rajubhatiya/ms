package com.agarment.OrderService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Entity
@Table(name="ORDER_DETAIL")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq")
    private long id;
    @Column(name="PRODUCT_ID")
    private long productId;
    @Column(name="QUANTITY")
    private long quanity;
    @Column(name="ORDER_DATE")
    private Instant orderDate;
    @Column(name="STATUS")
    private String orderStatus;
    @Column(name="TOTAL_AMOUNT")
    private long amount;
}
