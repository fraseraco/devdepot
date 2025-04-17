package com.swe.backend.DTOs;

import com.swe.backend.Entity.Product;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.swe.backend.Entity.Product}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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