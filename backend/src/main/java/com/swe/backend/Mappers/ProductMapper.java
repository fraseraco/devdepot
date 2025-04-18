package com.swe.backend.Mappers;


import com.swe.backend.DTOs.ProductDto;
import com.swe.backend.DTOs.SlimProductDto;
import com.swe.backend.Entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    static ProductDto toProductDto(Product product) {
        return null;
    }

    SlimProductDto toSlimProductDto(Product product);
    Product toProduct(ProductDto productDto);

}
