package com.swe.backend.DTOs;

import com.swe.backend.Entity.Cart;
import com.swe.backend.Entity.CartItem;
import com.swe.backend.Entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * DTO for {@link com.swe.backend.Entity.Cart}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto implements Serializable {
    @NotNull
    Long cartId;
    @NotNull
    String username;

    @NotNull
    Instant createdAt;

    Instant updatedAt;

    List<CartItemDto> cartItems;

    public CartDto(Cart cart, List<CartItem> cartItems) {
        this.cartId = cart.getId();
        this.username = cart.getUser().getUsername();  // assuming Cart has a User
        this.updatedAt = cart.getUpdatedAt();
        this.createdAt = cart.getCreatedAt();
        this.cartItems = cartItems.stream().map(CartItemDto::new).collect(Collectors.toList());
    }
}