package com.swe.backend.DTOs;

import com.swe.backend.Entity.OrderItem;
import com.swe.backend.Entity.OrderItemId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link OrderItem}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto implements Serializable {
    OrderItemId id;
    OrderDto order;
    ProductDto product;
    Integer quantity;
    BigDecimal pricePerUnit;
}