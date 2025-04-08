package com.swe.backend.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.swe.backend.Entity.Product;
import com.swe.backend.Entity.User;
import com.swe.backend.Service.ProductService;
import com.swe.backend.Service.UserService;
import com.swe.backend.Views.Views;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping(value = {"/product"})
public class ProductInfoController {

    private final ProductService productService;

    public ProductInfoController(ProductService productService) {
        this.productService = productService;
    }

    @JsonView(Views.Internal.class)
    @GetMapping("/{id}/detail")
    public ResponseEntity<Product> getProductInfo_Internal(@PathVariable Long id) {
        return productService.getProductByID(id);
    }

    @JsonView(Views.Public.class)
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductInfo_Public(@PathVariable Long id) {
        return productService.getProductByID(id);
    }
}
