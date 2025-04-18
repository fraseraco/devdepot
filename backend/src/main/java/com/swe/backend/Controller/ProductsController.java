package com.swe.backend.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.swe.backend.DTOs.ProductDto;
import com.swe.backend.Service.ProductService;
import com.swe.backend.Views.Views;

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

    @JsonView(Views.Public.class)
    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getProducts(){
        return productService.getProducts();
    }

    @JsonView(Views.Public.class)
    @GetMapping("/count")
    public ResponseEntity<Long> getProductsCount(){
        return productService.getCount();
    }

}
