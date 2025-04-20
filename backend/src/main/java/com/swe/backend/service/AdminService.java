package com.swe.backend.service;

import com.swe.backend.dtos.ProductDto;
import com.swe.backend.entity.Product;
import com.swe.backend.mappers.ProductMapper;
import com.swe.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public AdminService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductDto adminAddProduct(ProductDto productDto) {
        if (productRepository.existsById(productDto.getId())) {
            throw new IllegalArgumentException("Product already exists with id: " + productDto.getId());
        }
        Product product = productMapper.toProduct(productDto);
        Product saved = productRepository.save(product);
        return productMapper.toProductDto(saved);
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toProductDto)
                .collect(Collectors.toList());
    }

    public Optional<ProductDto> getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toProductDto);
    }

    public Optional<ProductDto> updateProduct(Long id, ProductDto productDto) {
        return productRepository.findById(id).map(product -> {
            product.setName(productDto.getName());
            product.setCategory(productDto.getCategory());
            product.setBrand(productDto.getBrand());
            product.setInventoryQty(productDto.getInventoryQty());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());
            product.setSku(productDto.getSku());
            product.setSpecifications(productDto.getSpecifications());
            return productMapper.toProductDto(productRepository.save(product));
        });
    }

    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}