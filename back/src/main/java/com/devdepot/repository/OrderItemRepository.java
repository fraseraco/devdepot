package com.devdepot.repository;

import com.devdepot.entity.OrderItem;
import com.devdepot.entity.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {
}