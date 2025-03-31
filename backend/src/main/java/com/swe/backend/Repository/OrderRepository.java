package com.swe.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swe.backend.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}