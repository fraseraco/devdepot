package com.swe.backend.mappers;

import com.swe.backend.dtos.CartItemDto;
import com.swe.backend.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CartItemMapper {

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