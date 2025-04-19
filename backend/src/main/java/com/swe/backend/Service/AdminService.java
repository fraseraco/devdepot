package com.swe.backend.Service;

import com.swe.backend.DTOs.ProductDto;
import com.swe.backend.Entity.Product;
import com.swe.backend.Mappers.ProductMapper;
import com.swe.backend.Repository.ProductRepository;
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

    public Optional<ProductDto> updateProduct(Long id, ProductDto dto) {
        return productRepository.findById(id).map(product -> {
            product.setName(dto.getName());
            product.setCategory(dto.getCategory());
            product.setBrand(dto.getBrand());
            product.setInventoryQty(dto.getInventoryQty());
            product.setPrice(dto.getPrice());
            product.setDescription(dto.getDescription());
            product.setSku(dto.getSku());
            product.setSpecifications(dto.getSpecifications());
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