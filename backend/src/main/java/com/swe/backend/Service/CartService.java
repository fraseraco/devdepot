package com.swe.backend.Service;

import com.swe.backend.DTOs.CartDto;
import com.swe.backend.DTOs.CartItemDto;
import com.swe.backend.Entity.Cart;
import com.swe.backend.Exceptions.CartException;
import com.swe.backend.Mappers.CartItemMapper;
import com.swe.backend.Mappers.CartMapper;
import com.swe.backend.Repository.CartItemRepository;
import com.swe.backend.Repository.CartRepository;
import com.swe.backend.Repository.ProductRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CartMapper cartMapper;
    private final CartItemMapper cartItemMapper;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, CartItemRepository cartItemRepository, CartMapper cartMapper, CartItemMapper cartItemMapper) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.cartMapper = cartMapper;
        this.cartItemMapper = cartItemMapper;
        this.productRepository = productRepository;
    }

    // id param here is the user id
    public ResponseEntity<List<CartDto>> getCartByUser(Long uid) {
        List<Cart> carts=  cartRepository.findAllByUserId(uid)
                .orElseThrow(() -> new CartException(uid));
        List<CartDto> cartDtos = carts.stream().map(cartMapper::toCartDto).toList();
        return ResponseEntity.ok(cartDtos);
    }

    public ResponseEntity<List<CartItemDto>> getCurrentCart(Long uid) {
        Cart cart = cartRepository.findCartByUserIdAndIsActive(uid, true)
                .orElseThrow(() -> new CartException(uid));

        List<CartItemDto> items = cartItemRepository.findAllByCart(cart)
                .orElse(List.of())
                .stream()
                .map(cartItemMapper::toCartItemDto)
                .toList();

        return ResponseEntity.ok(items);
    }

    public ResponseEntity<List<CartDto>> getCarts() {
        List<Cart> carts = cartRepository.findAll();
        List<CartDto> cartDtos = carts.stream()
                .map(cartMapper::toCartDto)
                .toList();
        return ResponseEntity.ok(cartDtos);
    }

    public ResponseEntity<Long> getCount() {
        Long count = cartRepository.count();
        return ResponseEntity.ok(count);
    }
}