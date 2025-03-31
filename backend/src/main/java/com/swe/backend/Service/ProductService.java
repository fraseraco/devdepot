package com.swe.backend.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonView;
import com.swe.backend.Entity.Product;
import com.swe.backend.Exceptions.ProductException;
import com.swe.backend.Repository.ProductRepository;
import com.swe.backend.Views.Views;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    public ResponseEntity<Product> getProductByID(Long id) {
        Product product =  productRepository.findById(id)
                .orElse(null);
        if (product == null) { throw new ProductException(id); }
        return ResponseEntity.ok(product);
    }

    @JsonView(Views.Public.class)
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productRepository.findAll();

        return ResponseEntity.ok(productRepository.findAll());
    }

    @JsonView(Views.Public.class)
    public ResponseEntity<Long> getCount() {
        Long count = productRepository.count();
        return ResponseEntity.ok(count);
    }
}