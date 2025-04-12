package com.swe.backend.Controller;

import com.swe.backend.DTOs.ProductDto;
import com.swe.backend.DTOs.UserDto;
import com.swe.backend.DTOs.UserRegistrationDto;
import com.swe.backend.Entity.Cart;
import com.swe.backend.Entity.Product;
import com.swe.backend.Entity.User;
import com.swe.backend.Service.AdminService;
import com.swe.backend.Service.CartService;
import com.swe.backend.Service.ProductService;
import com.swe.backend.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class Administration {
    private final ProductService productService;
    private final UserService userService;
    private final CartService cartService;
    private AdminService adminService;

    public Administration(ProductService productService, UserService userService, CartService cartService) {
        this.productService = productService;
        this.userService = userService;
        this.cartService = cartService;
    }

/* * * * * * User * * * * * */
@PostMapping("/add/user")
public ResponseEntity<UserDto> adminAddUser(@RequestBody UserRegistrationDto userRegistrationDto) {
    User user = userService.registerNewUser(userRegistrationDto);
    UserDto userDto = new UserDto(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
}


/* * * * * * Products * * * * * */
@PostMapping("/add/product")
public ResponseEntity<ProductDto> adminAddProduct(@RequestBody ProductDto productDto) {
    Product product = adminService.AdminAddProduct(productDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
}


///* * * * * * Cart * * * * * */
//@PostMapping("/")
//public ResponseEntity<UserDto> adminAddItemToCart(@RequestBody UserRegistrationDto userRegistrationDto) {
//    Cart cart = cartService
//    User user = userService.registerNewUser(userRegistrationDto);
//    UserDto userDto = new UserDto(user);
//    return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
//}

}