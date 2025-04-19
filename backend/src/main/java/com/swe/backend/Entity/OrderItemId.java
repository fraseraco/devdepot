package com.swe.backend.Entity;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrderItemId implements Serializable {
    public static final String COLUMN_ORDERID_NAME = "order_id";
    public static final String COLUMN_PRODUCTID_NAME = "product_id";
    private static final long serialVersionUID = 3174031597852486031L;

    private Long orderId;

    private Long productId;


    @Column(name = COLUMN_ORDERID_NAME, nullable = false)
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Column(name = COLUMN_PRODUCTID_NAME, nullable = false)
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderItemId entity = (OrderItemId) o;
        return Objects.equals(this.productId, entity.productId) &&
                Objects.equals(this.orderId, entity.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, orderId);
    }

}