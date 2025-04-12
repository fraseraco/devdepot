package com.swe.backend.DTOs;

import com.swe.backend.Entity.Product;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * DTO for {@link Product}
 */
@Value
public class ProductDto implements Serializable {
    @NotNull
    Long id;
    @NotNull
    String name;
    @NotNull
    String category;
    @NotNull
    String brand;
    @NotNull
    Integer inventoryQty;
    @NotNull
    BigDecimal price;
    String description;
    @NotNull
    String sku;
    @NotNull
    List<Map<String, Object>> specifications;
}