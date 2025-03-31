package com.swe.backend.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = Payment.TABLE_NAME, schema = "devdepot", indexes = {
        @Index(name = "fk_payments_orderid", columnList = "order_id")
})
public class Payment {
    public static final String TABLE_NAME = "payment";
    public static final String COLUMN_ID_NAME = "transaction_id";
    public static final String COLUMN_ORDERID_NAME = "order_id";
    public static final String COLUMN_PAYMENTMETHOD_NAME = "payment_method";
    public static final String COLUMN_SUBTOTAL_NAME = "subtotal";
    public static final String COLUMN_TAXTOTAL_NAME = "tax_total";
    public static final String COLUMN_PAYMENTSTATUS_NAME = "payment_status";
    public static final String COLUMN_PAYMENTDATE_NAME = "payment_date";


    private Long id;

    private Long orderId;

    private String paymentMethod;

    private BigDecimal subtotal;

    private BigDecimal taxTotal;

    private String paymentStatus;

    private Instant paymentDate;

    @Id
    @Column(name = COLUMN_ID_NAME, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = COLUMN_ORDERID_NAME, nullable = false)
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Lob
    @Column(name = COLUMN_PAYMENTMETHOD_NAME, nullable = false)
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Column(name = COLUMN_SUBTOTAL_NAME, nullable = false, precision = 10, scale = 2)
    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    @Column(name = COLUMN_TAXTOTAL_NAME, nullable = false, precision = 10, scale = 2)
    public BigDecimal getTaxTotal() {
        return taxTotal;
    }

    public void setTaxTotal(BigDecimal taxTotal) {
        this.taxTotal = taxTotal;
    }

    @Lob
    @Column(name = COLUMN_PAYMENTSTATUS_NAME)
    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = COLUMN_PAYMENTDATE_NAME)
    public Instant getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Instant paymentDate) {
        this.paymentDate = paymentDate;
    }

}