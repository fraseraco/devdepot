package com.devdepot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Embeddable
public class OrderItemId implements Serializable {
    public static final String COLUMN_ORDERID_NAME = "order_id";
    public static final String COLUMN_PRODUCTID_NAME = "product_id";
    private static final long serialVersionUID = -1332370531775171840L;

    private Long orderId;

    private Long productId;

    @Column(name = COLUMN_ORDERID_NAME, nullable = false)
    public Long getOrderId() {
        return orderId;
    }

    @Column(name = COLUMN_PRODUCTID_NAME, nullable = false)
    public Long getProductId() {
        return productId;
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