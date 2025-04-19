package com.swe.backend.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.swe.backend.DTOs.ProductDto;
import com.swe.backend.Mappers.ProductMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.swe.backend.Entity.Product;
import com.swe.backend.Exceptions.ProductException;
import com.swe.backend.Repository.ProductRepository;

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