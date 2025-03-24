package com.devdepot.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;

@Entity(name = Order.ENTITY_NAME)
@Table(name = Order.TABLE_NAME)
public class Order {
    public static final String ENTITY_NAME = "Order";
    public static final String TABLE_NAME = "`order`";
    public static final String COLUMN_ID_NAME = "order_id";
    public static final String COLUMN_ORDERDATE_NAME = "order_date";
    public static final String COLUMN_TOTALCOST_NAME = "total_cost";
    public static final String COLUMN_SHIPPINGADDRESS_NAME = "shipping_address";
    public static final String COLUMN_ORDERSTATUS_NAME = "order_status";
    public static final String COLUMN_TRACKINGNUM_NAME = "tracking_num";
    public static final String COLUMN_DISCOUNTPROMOTION_NAME = "discount_promotion";


    private Long id;

    private User customer;

    private Instant orderDate;

    private BigDecimal totalCost;

    private String shippingAddress;

    private String orderStatus;

    private String trackingNum;

    private BigDecimal discountPromotion;

    private Payment transaction;

    @Id
    @Column(name = COLUMN_ID_NAME, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "customer_id", nullable = false)
    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = COLUMN_ORDERDATE_NAME)
    public Instant getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Instant orderDate) {
        this.orderDate = orderDate;
    }

    @Column(name = COLUMN_TOTALCOST_NAME, nullable = false, precision = 10, scale = 2)
    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    @Column(name = COLUMN_SHIPPINGADDRESS_NAME, nullable = false)
    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @ColumnDefault("'PROCESSED'")
    @Lob
    @Column(name = COLUMN_ORDERSTATUS_NAME, nullable = false)
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Column(name = COLUMN_TRACKINGNUM_NAME, length = 50)
    public String getTrackingNum() {
        return trackingNum;
    }

    public void setTrackingNum(String trackingNum) {
        this.trackingNum = trackingNum;
    }

    @ColumnDefault("0.00")
    @Column(name = COLUMN_DISCOUNTPROMOTION_NAME, precision = 5, scale = 2)
    public BigDecimal getDiscountPromotion() {
        return discountPromotion;
    }

    public void setDiscountPromotion(BigDecimal discountPromotion) {
        this.discountPromotion = discountPromotion;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "transaction_id", nullable = false)
    public Payment getTransaction() {
        return transaction;
    }

    public void setTransaction(Payment transaction) {
        this.transaction = transaction;
    }

}