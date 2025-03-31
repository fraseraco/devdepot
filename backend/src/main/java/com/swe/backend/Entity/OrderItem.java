package com.swe.backend.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Entity
@Table(name = OrderItem.TABLE_NAME, schema = "devdepot", indexes = {
        @Index(name = "product_id", columnList = "product_id")
})
public class OrderItem {
    public static final String TABLE_NAME = "order_item";
    public static final String COLUMN_QUANTITY_NAME = "quantity";
    public static final String COLUMN_PRICEPERUNIT_NAME = "price_per_unit";

    private OrderItemId id;

    private Order order;

    private Product product;

    private Integer quantity;

    private BigDecimal pricePerUnit;

    @EmbeddedId
    public OrderItemId getId() {
        return id;
    }

    public void setId(OrderItemId id) {
        this.id = id;
    }

    @MapsId("orderId")
    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "order_id", nullable = false)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @MapsId("productId")
    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "product_id", nullable = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @ColumnDefault("1")
    @Column(name = COLUMN_QUANTITY_NAME, nullable = false)
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Column(name = COLUMN_PRICEPERUNIT_NAME, nullable = false, precision = 10, scale = 2)
    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

}