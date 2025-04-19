package com.swe.backend.DTOs;

import com.swe.backend.Entity.Order;
import com.swe.backend.Entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO for {@link Order}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto implements Serializable {
    Long id;
    UserDto user;
    Instant orderDate;
    BigDecimal totalCost;
    String shippingAddress;
    String orderStatus;
    String trackingNum;
    BigDecimal discountPromotion;
    PaymentDto transaction;
    List<OrderItemDto> orderItems;
}