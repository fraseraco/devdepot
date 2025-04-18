package com.swe.backend.Service;

import java.util.List;

import com.swe.backend.DTOs.ProductDto;
import com.swe.backend.Mappers.ProductMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.swe.backend.Entity.Product;
import com.swe.backend.Exceptions.ProductException;
import com.swe.backend.Repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

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
        List<ProductDto> productDtos = productMapper.map(products);
        return ResponseEntity.ok(productDtos);
    }

    public ResponseEntity<Long> getCount() {
        Long count = productRepository.count();
        return ResponseEntity.ok(count);
    }
}