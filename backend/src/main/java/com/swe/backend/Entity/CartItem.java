package com.swe.backend.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Entity
@Table(name = CartItem.TABLE_NAME, schema = "devdepot", indexes = {
        @Index(name = "cart_id", columnList = "cart_id"),
        @Index(name = "product_id", columnList = "product_id")
})
public class CartItem {
    public static final String TABLE_NAME = "cart_item";
    public static final String COLUMN_ID_NAME = "cart_item_id";
    public static final String COLUMN_QUANTITY_NAME = "quantity";
    public static final String COLUMN_ADDEDAT_NAME = "added_at";


    private Long id;

    private Cart cart;

    private Product product;

    private Integer quantity;

    private Instant addedAt;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_ID_NAME, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "cart_id", nullable = false)
    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @NotNull
    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "product_id", nullable = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @NotNull
    @ColumnDefault("1")
    @Column(name = COLUMN_QUANTITY_NAME, nullable = false)
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = COLUMN_ADDEDAT_NAME)
    public Instant getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Instant addedAt) {
        this.addedAt = addedAt;
    }

}