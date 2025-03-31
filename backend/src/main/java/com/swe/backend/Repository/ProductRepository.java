package com.swe.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swe.backend.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}