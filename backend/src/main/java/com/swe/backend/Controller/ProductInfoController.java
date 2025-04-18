package com.swe.backend.Controller;

import com.swe.backend.DTOs.ProductDto;
import com.swe.backend.Service.ProductService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = {"/product"})
public class ProductInfoController {

    private final ProductService productService;

    public ProductInfoController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<ProductDto> getProductInfo_Internal(@PathVariable Long id) {
        return productService.getProductByID(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductInfo_Public(@PathVariable Long id) {
        return productService.getProductByID(id);
    }
}
