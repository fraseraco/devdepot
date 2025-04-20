package com.swe.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swe.backend.entity.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductByName(String name);
     Optional<Product> findProductById(Long id);

    boolean existsProductByName(String name);

    boolean existsByName(String name);
}