package com.swe.backend.Controller;

import com.swe.backend.DTOs.ProductDto;
import com.swe.backend.Service.ProductService;

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

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getProductsCount(){
        return productService.getCount();
    }

}
