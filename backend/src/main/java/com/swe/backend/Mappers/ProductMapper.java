package com.swe.backend.Mappers;


import com.swe.backend.DTOs.ProductDto;
import com.swe.backend.DTOs.SlimProductDto;
import com.swe.backend.Entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto productToProductDto(Product product);
    SlimProductDto productToSlimProductDto(Product product);
    Product productDtoToProduct(ProductDto productDto);

}
