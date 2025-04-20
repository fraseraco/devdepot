package com.swe.backend.service;

import java.util.List;
import java.util.Optional;

import com.swe.backend.dtos.ProductDto;
import com.swe.backend.mappers.ProductMapper;
import org.springframework.stereotype.Service;

import com.swe.backend.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }
    
    public Optional<ProductDto> getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toProductDto);
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toProductDto)
                .toList();
    }

    public Long getProductCount() {
        return productRepository.count();
    }

    public Optional<ProductDto> getProductDetailById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toProductDto);
    }
}