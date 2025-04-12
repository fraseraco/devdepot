package com.swe.backend.DTOs;

import com.swe.backend.Entity.Product;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.swe.backend.Entity.Product}
 */
@Value
public class SlimProductDto implements Serializable {
    Long id;
    String name;
    BigDecimal price;

    public SlimProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
    }
}