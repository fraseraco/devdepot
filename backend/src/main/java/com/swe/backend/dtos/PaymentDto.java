package com.swe.backend.dtos;

import com.swe.backend.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for {@link Payment}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto implements Serializable {
    Long id;
    Long orderId;
    String paymentMethod;
    BigDecimal subtotal;
    BigDecimal taxTotal;
    String paymentStatus;
    Instant paymentDate;
}