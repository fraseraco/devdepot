package com.swe.backend.Controller;

import com.swe.backend.DTOs.ProductDto;
import com.swe.backend.DTOs.UserDto;
import com.swe.backend.DTOs.UserRegistrationDto;
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
    private final UserService userService;
    private AdminService adminService;

    public Administration(ProductService productService, UserService userService, CartService cartService) {
        this.userService = userService;
    }

/* * * * * * User * * * * * */
@PostMapping("/add/user")
public ResponseEntity<UserDto> adminAddUser(@RequestBody UserRegistrationDto userRegistrationDto) {
    UserDto user = userService.registerNewUser(userRegistrationDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(user);
}


/* * * * * * Products * * * * * */
@PostMapping("/add/product")
public ResponseEntity<ProductDto> adminAddProduct(@RequestBody ProductDto productDto) {
    ProductDto addedProduct = adminService.AdminAddProduct(productDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(addedProduct);
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