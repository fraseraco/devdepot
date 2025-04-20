package com.swe.backend.dtos;

import com.swe.backend.entity.Cart;
import com.swe.backend.entity.CartItem;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO for {@link com.swe.backend.entity.Cart}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto implements Serializable {
    @NotNull
    Long id;
    @NotNull
    String username;

    @NotNull
    Instant createdAt;

    Instant updatedAt;

    @NotNull
    private Boolean isActive;

    List<CartItemDto> cartItems;

    public CartDto(Cart cart, List<CartItem> cartItems) {
        this.id = cart.getId();
        this.username = cart.getUser().getUsername();  // assuming Cart has a User
        this.updatedAt = cart.getUpdatedAt();
        this.createdAt = cart.getCreatedAt();
        this.cartItems = cartItems.stream().map(CartItemDto::new).collect(Collectors.toList());
        this.isActive = cart.getIsActive();
    }
}