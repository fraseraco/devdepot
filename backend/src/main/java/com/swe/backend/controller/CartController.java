package com.swe.backend.controller;

import com.swe.backend.dtos.AddItemRequestDto;
import com.swe.backend.dtos.CartDto;
import com.swe.backend.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")

public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/me")
    public CartDto myCart() { return cartService.myCart(); }

    @PostMapping("/items")
    public CartDto addItem(@RequestBody AddItemRequestDto r) {
        return cartService.addItem(r.getProductId(), r.getQuantity());
    }

    @PutMapping("/items")
    public CartDto updateItem(@RequestBody AddItemRequestDto update) {
        return cartService.updateItemQuantity(update.getProductId(), update.getQuantity());
    }

    @DeleteMapping
    public ResponseEntity<Void> clear() {
        cartService.clear();
        return ResponseEntity.noContent().build();
    }
}