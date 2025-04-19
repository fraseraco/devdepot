package com.swe.backend.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutRequestDto implements Serializable {
    @NotBlank String shippingAddress;
    @NotBlank String paymentMethod;   // e.g. "CARD", "PAYPAL"
    String discountCode;
}
