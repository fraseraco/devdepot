package com.swe.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = Cart.TABLE_NAME, schema = "devdepot", indexes = {
        @Index(name = "user_id", columnList = "user_id")
})
public class Cart{
    public static final String TABLE_NAME = "cart";
    public static final String COLUMN_ID_NAME = "cart_id";
    public static final String COLUMN_CREATEDAT_NAME = "created_at";
    public static final String COLUMN_UPDATEDAT_NAME = "updated_at";
    public static final String COLUMN_USERID_NAME = "user_id";
    public static final String COLUMN_ISACTIVE_NAME = "is_active";

    @Setter
    private Long id;

    @Setter
    private User user;

    @Setter
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = COLUMN_ISACTIVE_NAME)
    private Boolean isActive;

    @Setter
    private List<CartItem> cartItems = new ArrayList<>();


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_ID_NAME, insertable = false, updatable = false, nullable = false)
    public Long getId() {
        return id;
    }

    @NotNull
    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = COLUMN_CREATEDAT_NAME, updatable = false)
    public Instant getCreatedAt() {
        return createdAt;
    }

    @Column(name = COLUMN_ISACTIVE_NAME, nullable = false)
    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Column(name = COLUMN_UPDATEDAT_NAME)
    public Instant getUpdatedAt() {
        return updatedAt;
    }

    @UpdateTimestamp
    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @OneToMany(mappedBy = "cart",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    public List<CartItem> getCartItems() { return cartItems; }

    public void addOrIncrementItem(Product product, int qtyToAdd) {
        if (qtyToAdd <= 0) throw new IllegalArgumentException("qtyToAdd must be positive");

        CartItem item = cartItems.stream()
                .filter(ci -> ci.getProduct().equals(product))
                .findFirst()
                .orElseGet(() -> {
                    CartItem ci = new CartItem(product, 0);
                    cartItems.add(ci);
                    return ci;
                });

        item.setQuantity(item.getQuantity() + qtyToAdd);
    }

}