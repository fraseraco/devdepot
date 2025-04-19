package com.swe.backend.Repository;

import com.swe.backend.Entity.Cart;
import com.swe.backend.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<List<CartItem>> findByCart(Cart cart);
    Optional<List<CartItem>> findAllByCartId(long cartId);
    Optional<List<CartItem>> findAllByCart(Cart cart);
}