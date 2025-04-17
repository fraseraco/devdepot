package com.swe.backend.DTOs;

import com.swe.backend.Entity.Product;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * DTO for {@link Product}
 */
@Data
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

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.category = product.getCategory();
        this.brand = product.getBrand();
        this.inventoryQty = product.getInventoryQty();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.sku = product.getSku();
        this.specifications = product.getSpecifications();
    }
}