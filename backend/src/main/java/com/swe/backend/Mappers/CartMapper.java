package com.swe.backend.Mappers;

import com.swe.backend.DTOs.CartDto;
import com.swe.backend.Entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface  CartMapper {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "user", ignore = true)
    Cart toCart(CartDto cartDto);

    @Mapping(source = "id", target = "cartId")
    @Mapping(source = "user.username", target = "username")
    @Mapping(target = "cartItems", ignore = true)
    CartDto toCartDto(Cart cart);

}