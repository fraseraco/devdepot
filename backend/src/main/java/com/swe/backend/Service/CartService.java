package com.swe.backend.Service;

import com.swe.backend.DTOs.CartDto;
import com.swe.backend.DTOs.CartItemDto;
import com.swe.backend.Entity.Cart;
import com.swe.backend.Exceptions.CartException;
import com.swe.backend.Mappers.CartMapper;
import com.swe.backend.Repository.CartItemRepository;
import com.swe.backend.Repository.CartRepository;
import com.swe.backend.Repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, CartItemRepository cartItemRepository) {this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    // id param here is the user id
    public ResponseEntity<List<CartDto>> getCartByUser(Long uid) {
        List<Cart> carts=  cartRepository.findAllByUserId(uid);
        if (carts == null) { throw new CartException(uid); }
        List<CartDto> cartDtos = carts.stream().map(CartMapper::toCartDto).toList();
        return ResponseEntity.ok(cartDtos);
    }

    public ResponseEntity<List<CartItemDto>> getCurrentCartContents(Long uid) {
        Cart cart =  cartRepository.findCartByUserIdAndIsActive(uid, true);
        if (cart == null) { throw new CartException(uid); }
        List<CartItemDto> items = cartItemRepository.findByCart(cart).stream().map(CartItemDto::new).toList();
        return ResponseEntity.ok(items);
    }

    public ResponseEntity<List<CartDto>> getCarts() {
        List<Cart> carts = cartRepository.findAll();
        List<CartDto> cartDtos = carts.stream()
                .map(CartMapper::toCartDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(cartDtos);
    }

    public ResponseEntity<Long> getCount() {
        Long count = cartRepository.count();
        return ResponseEntity.ok(count);
    }
}