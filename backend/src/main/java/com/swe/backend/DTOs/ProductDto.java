package com.swe.backend.DTOs;

import com.swe.backend.Entity.Product;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable {
    @NotNull private Long id;
    @NotNull private String name;
    @NotNull private String category;
    @NotNull private String brand;
    @NotNull private Integer inventoryQty;
    @NotNull private BigDecimal price;
    private String description;
    @NotNull private String sku;
    @NotNull private Map<String, Object> specifications;

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
