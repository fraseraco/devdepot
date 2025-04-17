package com.swe.backend.Mappers;

import com.swe.backend.DTOs.CartItemDto;
import com.swe.backend.DTOs.SlimProductDto;
import com.swe.backend.Entity.CartItem;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.io.Serializable;
import java.time.Instant;


@Mapper
public interface CartItemMapper {
    CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);
    CartItemDto toCartItemDto(CartItem cartItem);
    CartItem toCartItem(CartItemDto cartItemDto);
}