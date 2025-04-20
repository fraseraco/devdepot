package com.swe.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swe.backend.entity.OrderItem;
import com.swe.backend.entity.OrderItemId;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {
}