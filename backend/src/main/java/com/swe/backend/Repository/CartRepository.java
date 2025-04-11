package com.swe.backend.Repository;

import com.swe.backend.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUserId(Long id);

    Cart findByUserIdAndId(Long userId, Long id);
}