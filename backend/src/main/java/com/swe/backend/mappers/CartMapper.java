package com.swe.backend.mappers;

import com.swe.backend.dtos.CartDto;
import com.swe.backend.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CartItemMapper.class)
public interface CartMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "isActive", target = "isActive")
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "cartItems", ignore = true)
    Cart toCart(CartDto cartDto);


    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "isActive", target = "isActive")
    CartDto toCartDto(Cart cart);
}