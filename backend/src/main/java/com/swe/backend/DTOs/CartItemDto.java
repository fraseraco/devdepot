package com.swe.backend.DTOs;

import com.swe.backend.Entity.CartItem;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.swe.backend.Entity.CartItem}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto implements Serializable {
    Long id;
    @NotNull
    Long cartId;
    @NotNull
    SlimProductDto product;
    @NotNull
    Integer quantity;
    Instant addedAt;

    public CartItemDto(CartItem item) {
        this.id = item.getId();
        this.cartId = item.getCart().getId();
        this.product = new SlimProductDto(item.getProduct());
        this.quantity = item.getQuantity();
        this.addedAt = item.getAddedAt();
    }
}