package com.swe.backend.Mappers;

import com.swe.backend.DTOs.ProductDto;
import com.swe.backend.DTOs.SlimProductDto;
import com.swe.backend.Entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toProductDto(Product product);
    List<ProductDto> map(List<Product> product);
    SlimProductDto toSlimProductDto(Product product);
    Product toProduct(ProductDto productDto);
}
