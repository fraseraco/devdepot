package com.swe.backend.Mappers;

import com.swe.backend.DTOs.CartDto;
import com.swe.backend.Entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CartMapper {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);
    Cart toCart(CartDto cartDto);
    CartDto toCartDto(Cart cart);
}