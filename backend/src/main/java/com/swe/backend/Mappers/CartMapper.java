package com.swe.backend.Mappers;

import com.swe.backend.DTOs.CartDto;
import com.swe.backend.Entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {

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