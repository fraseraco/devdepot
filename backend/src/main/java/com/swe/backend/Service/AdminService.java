package com.swe.backend.Service;

import com.swe.backend.DTOs.ProductDto;

import com.swe.backend.Entity.Role;
import com.swe.backend.Entity.Product;
import com.swe.backend.Repository.CartRepository;
import com.swe.backend.Repository.ProductRepository;
import com.swe.backend.Repository.UserRepository;
import org.antlr.v4.runtime.misc.LogManager;

import java.time.Instant;

public class AdminService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    public AdminService(ProductRepository productRepository, UserRepository userRepository, CartRepository cartRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    public Product AdminAddProduct(ProductDto productInfo) {
         if (productRepository.existsProductByName(productInfo.getName())){
            throw new IllegalArgumentException("Product name already taken.");
        }

        Product product = new Product();
        product.setId(product.getId());
        product.setName(product.getName());
        product.setCategory(product.getCategory());
        product.setBrand(product.getBrand());
        product.setInventoryQty(product.getInventoryQty());
        product.setPrice(product.getPrice());
        product.setDescription(product.getDescription());
        product.setSku(product.getSku());
        product.setSpecifications(product.getSpecifications());
        return productRepository.save(product);
    }
}
