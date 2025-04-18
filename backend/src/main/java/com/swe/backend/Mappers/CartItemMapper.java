package com.swe.backend.Mappers;

import com.swe.backend.DTOs.CartItemDto;
import com.swe.backend.Entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CartItemMapper {
    CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);

    @Mapping(source = "cart.id", target = "cartId")
    CartItemDto toCartItemDto(CartItem cartItem);

    @Mapping(target = "product.category", ignore = true)
    @Mapping(target = "product.brand", ignore = true)
    @Mapping(target = "product.inventoryQty", ignore = true)
    @Mapping(target = "product.description", ignore = true)
    @Mapping(target = "product.sku", ignore = true)
    @Mapping(target = "product.specifications", ignore = true)
    @Mapping(target = "cart", ignore = true)
    CartItem toCartItem(CartItemDto cartItemDto);
}