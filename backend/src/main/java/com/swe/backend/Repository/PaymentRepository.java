package com.swe.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swe.backend.Entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}