package com.swe.backend.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.swe.backend.DTOs.ProductDto;
import com.swe.backend.DTOs.UserDto;
import com.swe.backend.DTOs.UserRegistrationDto;
import com.swe.backend.Entity.Product;
import com.swe.backend.Entity.User;
import com.swe.backend.Service.ProductService;
import com.swe.backend.Views.Views;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductsController {
    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

//    @PostMapping("/add")
//    public ResponseEntity<ProductDto> registerUser(@RequestBody UserRegistrationDto userRegistrationDto) {
//        User user = userService.registerNewUser(userRegistrationDto);
//        UserDto userDto = new UserDto(user);
//        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
//    }

    @JsonView(Views.Public.class)
    @GetMapping("/all")
    public ResponseEntity<List<Product>>  getProducts(){
        return productService.getProducts();
    }

    @JsonView(Views.Public.class)
    @GetMapping("/count")
    public ResponseEntity<Long> getProductsCount(){
        return productService.getCount();
    }

}
