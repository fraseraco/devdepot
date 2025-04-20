package com.swe.backend.repository;

import com.swe.backend.entity.Cart;
import com.swe.backend.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<List<CartItem>> findByCart(Cart cart);
    Optional<List<CartItem>> findAllByCartId(long cartId);
    Optional<List<CartItem>> findAllByCart(Cart cart);
}