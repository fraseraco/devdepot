package com.swe.backend.dtos;

import com.swe.backend.entity.Product;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.swe.backend.entity.Product}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlimProductDto implements Serializable {

    @NotNull
    Long id;
    @NotNull
    String name;
    @NotNull
    BigDecimal price;
    @NotNull
    String sku;


    public SlimProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.sku = product.getSku();
    }
}
