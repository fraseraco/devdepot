package com.swe.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swe.backend.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}