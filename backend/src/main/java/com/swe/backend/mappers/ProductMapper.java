package com.swe.backend.mappers;


import com.swe.backend.dtos.ProductDto;
import com.swe.backend.dtos.SlimProductDto;
import com.swe.backend.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto toProductDto(Product product);
    
    SlimProductDto toSlimProductDto(Product product);
    Product toProduct(ProductDto productDto);

}
