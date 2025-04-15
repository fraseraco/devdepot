package com.swe.backend.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.swe.backend.Entity.Cart;
import com.swe.backend.Service.CartService;
import com.swe.backend.Util.AddCartItemRequest;
import com.swe.backend.Views.Views;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) { this.cartService = cartService; }

    @JsonView(Views.Internal.class)
    @GetMapping("/all")
    public ResponseEntity<List<Cart>> getCart(){
        return cartService.getCarts();
    }

    @GetMapping("/{uid}")
    public ResponseEntity<List<Cart>> getCart(@PathVariable Long uid){
        return cartService.getCartByUser(uid);
    }

//    @PostMapping("/insert/{uid}/{cartId}/{productId}/{qty}")
//    public ResponseEntity<List<Cart>> addItemToSpecificCart(@PathVariable Long uid, @PathVariable Long cartId, @PathVariable Long productId, @PathVariable Integer qty){
////        Cart cart = cartService.addItemToCart(uid, cartId, itemRequest);
//        AddCartItemRequest itemRequest = new AddCartItemRequest(productId, qty);
//        return ResponseEntity.ok(cartService.addItemToCart(uid, cartId, itemRequest));
//    }

//    @PostMapping("/{uid}/{productId}/{qty}")
//    public ResponseEntity<List<Cart>> addItemToCart(@PathVariable Long uid, @PathVariable Long productId, @PathVariable Integer qty){
////        Cart cart = cartService.addItemToCart(uid, cartId, itemRequest);
//        AddCartItemRequest itemRequest = new AddCartItemRequest(productId, qty);
//        return ResponseEntity.ok(cartService.addItemToCart(uid, itemRequest));
//    }

    @JsonView(Views.Public.class)
    @GetMapping("/count")
    public ResponseEntity<Long>  getAllCartsCount(){
        return cartService.getCount();
    }

}