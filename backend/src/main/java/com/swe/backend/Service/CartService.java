package com.swe.backend.Service;

import com.swe.backend.DTOs.CartDto;
import com.swe.backend.Entity.Cart;
import com.swe.backend.Entity.CartItem;
import com.swe.backend.Entity.Product;
import com.swe.backend.Entity.User;
import com.swe.backend.Mappers.CartMapper;
import com.swe.backend.Repository.CartRepository;
import com.swe.backend.Repository.ProductRepository;
import com.swe.backend.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepo;
    private final ProductRepository productRepo;
    private final UserRepository userRepo;
    private final UserService userService;
    private final CartMapper cartMapper;

    private User currentUser() {
        UserDetails principal =
                (UserDetails) SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getPrincipal();
        return userRepo.findByUsername(principal.getUsername()).orElseThrow();
    }

    public CartDto myCart() {
        Cart cart = cartRepo.findByUserAndIsActive(currentUser(), true)
                .orElseGet(this::createNewCart);
        return cartMapper.toCartDto(cart);
    }

    public CartDto addItem(Long productId, int qty) {
        Cart cart = cartRepo.findByUserAndIsActive(currentUser(), true)
                .orElseGet(this::createNewCart);
        Product product = productRepo.findById(productId).orElseThrow();
        cart.addOrIncrementItem(product, qty);
        return cartMapper.toCartDto(cartRepo.save(cart));
    }

    public void clear() {
        cartRepo.findByUserAndIsActive(currentUser(), true)
                .ifPresent(cart -> cart.setIsActive(false));
    }

    private Cart createNewCart() {
        Cart c = new Cart();
        c.setUser(currentUser());
        c.setIsActive(true);
        return cartRepo.save(c);
    }

    public CartDto updateItemQuantity(Long productId, int qty) {
        if (qty < 0) throw new IllegalArgumentException("Quantity must be >= 0");

        Cart cart = cartRepo.findByUserAndIsActive(currentUser(), true)
                .orElseGet(this::createNewCart);

        // look for existing line item
        CartItem target = cart.getCartItems().stream()
                .filter(ci -> ci.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (target == null) {
            throw new IllegalArgumentException("Product not in cart");
        }

        if (qty == 0) {                   // remove if qty == 0
            cart.getCartItems().remove(target);
        } else {                          // update quantity
            target.setQuantity(qty);
        }

        return cartMapper.toCartDto(cartRepo.save(cart));
    }
}
