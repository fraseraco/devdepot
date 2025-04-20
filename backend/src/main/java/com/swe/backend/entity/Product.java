package com.swe.backend.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = Product.TABLE_NAME, schema = "devdepot", uniqueConstraints = {
        @UniqueConstraint(name = "name", columnNames = {"name"}),
        @UniqueConstraint(name = "sku", columnNames = {"sku"})
})
public class Product {
    public static final String TABLE_NAME = "product";
    public static final String COLUMN_ID_NAME = "product_id";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_CATEGORY_NAME = "category";
    public static final String COLUMN_BRAND_NAME = "brand";
    public static final String COLUMN_INVENTORYQTY_NAME = "inventory_qty";
    public static final String COLUMN_PRICE_NAME = "price";
    public static final String COLUMN_DESCRIPTION_NAME = "description";
    public static final String COLUMN_SKU_NAME = "sku";
    public static final String COLUMN_SPECIFICATIONS_NAME = "specifications";


    private Long id;

    private String name;

    private String category;

    private String brand;

    private Integer inventoryQty;

    private BigDecimal price;

    private String description;

    private String sku;

    private List<Map<String, Object>> specifications;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_ID_NAME, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = COLUMN_NAME_NAME, nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = COLUMN_CATEGORY_NAME, nullable = false, length = 25)
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    @Column(name = COLUMN_BRAND_NAME, nullable = false, length = 50)
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


    @ColumnDefault("0")
    @Column(name = COLUMN_INVENTORYQTY_NAME, nullable = false)
    public Integer getInventoryQty() {
        return inventoryQty;
    }

    public void setInventoryQty(Integer inventoryQty) {
        this.inventoryQty = inventoryQty;
    }


    @Column(name = COLUMN_PRICE_NAME, nullable = false, precision = 10, scale = 2)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Lob
    @Column(name = COLUMN_DESCRIPTION_NAME)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = COLUMN_SKU_NAME, nullable = false, length = 16)
    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }


    @Column(name = COLUMN_SPECIFICATIONS_NAME, nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    public List<Map<String, Object>> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<Map<String, Object>> specifications) {
        this.specifications = specifications;
    }

}