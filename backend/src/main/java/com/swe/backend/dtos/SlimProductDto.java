package com.swe.backend.dtos;

import com.swe.backend.entity.Product;
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
    Long id;
    String name;
    BigDecimal price;
    String sku;


    public SlimProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.sku = product.getSku();
    }
}