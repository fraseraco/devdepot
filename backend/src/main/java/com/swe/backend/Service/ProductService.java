package com.swe.backend.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.swe.backend.DTOs.ProductDto;
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
    
    public ResponseEntity<ProductDto> getProductByID(Long id) {
        Product product =  productRepository.findById(id)
                .orElse(null);
        if (product == null) { throw new ProductException(id); }
        ProductDto productDto = new ProductDto(product);
        return ResponseEntity.ok(productDto);
    }

    public ResponseEntity<List<ProductDto>> getProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {throw new ProductException();}
        List<ProductDto> productDtos = products.stream().map(ProductDto::new).toList();
        return ResponseEntity.ok(productDtos);
    }

    public ResponseEntity<Long> getCount() {
        Long count = productRepository.count();
        return ResponseEntity.ok(count);
    }
}