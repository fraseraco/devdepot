package com.swe.backend.Repository;

import com.swe.backend.Entity.Cart;
import com.swe.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<List<Cart>> findAllByUserId(Long id);

    Optional<Cart> findByUserIdAndIsActive(Long uid, boolean is_active);

    Optional<Cart> findByUserAndIsActive(User user, Boolean isActive);
}