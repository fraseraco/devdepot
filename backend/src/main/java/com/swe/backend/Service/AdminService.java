package com.swe.backend.Service;

import com.swe.backend.DTOs.ProductDto;

import com.swe.backend.Entity.Product;
import com.swe.backend.Mappers.ProductMapper;
import com.swe.backend.Repository.CartRepository;
import com.swe.backend.Repository.ProductRepository;
import com.swe.backend.Repository.UserRepository;
import io.micrometer.observation.ObservationFilter;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
    public AdminService(ProductRepository productRepository, UserRepository userRepository, CartRepository cartRepository) {
        this.productRepository = productRepository;
    }

    public ProductDto AdminAddProduct(ProductDto productDto) {
         if (productRepository.existsProductByName(productDto.getName())){
            throw new IllegalArgumentException("Product name already taken.");
        }
        Product p = new Product();
        p.setId(productDto.getId());
        p.setName(productDto.getName());
        p.setCategory(productDto.getCategory());
        p.setBrand(productDto.getBrand());
        p.setInventoryQty(productDto.getInventoryQty());
        p.setPrice(productDto.getPrice());
        p.setDescription(productDto.getDescription());
        p.setSku(productDto.getSku());
        p.setSpecifications(productDto.getSpecifications());
        ProductDto productDto1;
        productDto1 = productMapper.toProductDto(productRepository.save(p));
        return productDto1;
    }

    public ProductDto adminAddProduct(ProductDto productDto) {
        return AdminAddProduct(productDto);
    }

    public List<ProductDto> getAllProducts() {
        return null;
    }

    public ObservationFilter getProductById(Long id) {
        return null;
    }

    public ObservationFilter updateProduct(Long id, ProductDto productDto) {
        return null;
    }

    public boolean deleteProduct(Long id) {
        return false;
    }
}
