package com.swe.backend.DTOs;

import com.swe.backend.Entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for {@link Order}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto implements Serializable {
    Long id;
    UserDto customer;
    Instant orderDate;
    BigDecimal totalCost;
    String shippingAddress;
    String orderStatus;
    String trackingNum;
    BigDecimal discountPromotion;
    PaymentDto transaction;
}