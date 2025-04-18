package com.swe.backend.Service;

import com.swe.backend.DTOs.CartDto;
import com.swe.backend.DTOs.ProductDto;

import com.swe.backend.DTOs.UserDto;
import com.swe.backend.Entity.Product;
import com.swe.backend.Mappers.CartMapper;
import com.swe.backend.Mappers.ProductMapper;
import com.swe.backend.Mappers.UserMapper;
import com.swe.backend.Repository.CartRepository;
import com.swe.backend.Repository.ProductRepository;
import com.swe.backend.Repository.UserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final UserMapper userMapper;
    private final CartMapper cartMapper;
    private final ProductMapper productMapper;
    private final CartService cartService;

    public AdminService(ProductRepository productRepository, UserRepository userRepository, CartRepository cartRepository, UserMapper userMapper, CartMapper cartMapper, ProductMapper productMapper, CartService cartService) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.userMapper = userMapper;
        this.cartMapper = cartMapper;
        this.productMapper = productMapper;
        this.cartService = cartService;
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
        return productMapper.toProductDto(productRepository.save(p));

    }

    public ProductDto adminAddProduct(ProductDto productDto) {
        return AdminAddProduct(productDto);
    }

    public List<ProductDto> getAllProducts() {
        return null;
    }

    public Optional<ProductDto> getProductById(Long id) {
        return null;
    }

    public Optional<ProductDto> updateProduct(Long id, ProductDto productDto) {
        return null;
    }

    public boolean deleteProduct(Long id) {
        return false;
    }

    public ProductDto AdminAddProductToUser(ProductDto productDto, Long uid) {
        if (!(userRepository.existsById(uid))) { throw new IllegalArgumentException("User not found."); }
        UserDto user = userMapper.toUserDto(userRepository.getUserById(uid));
        
        CartDto cart = cartMapper.toCartDto(cartRepository.findCartByUserIdAndIsActive(uid, true));
        return productDto;
    }
}
