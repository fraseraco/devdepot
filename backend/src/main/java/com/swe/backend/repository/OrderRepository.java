package com.swe.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swe.backend.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}