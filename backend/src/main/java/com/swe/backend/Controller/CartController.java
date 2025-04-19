package com.swe.backend.Controller;

import com.swe.backend.DTOs.CartDto;
import com.swe.backend.Service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) { this.cartService = cartService; }


//    GET /cart → get current user’s active cart
//    POST /cart/items → add item to cart
//    PUT /cart/items/{productId} → update item quantity
//    DELETE /cart/items/{productId} → remove item from cart
//    GET /cart/count → get number of items (optional)


    @GetMapping("/all")
    public ResponseEntity<List<CartDto>> getCart(){
        return cartService.getCarts();
    }

    @GetMapping("/{uid}")
    public ResponseEntity<List<CartDto>> getCart(@PathVariable Long uid){
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

    @GetMapping("/count")
    public ResponseEntity<Long>  getAllCartsCount(){
        return cartService.getCount();
    }

}