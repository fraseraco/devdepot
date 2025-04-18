package com.swe.backend.Mappers;

import com.swe.backend.DTOs.OrderItemDto;
import com.swe.backend.Entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface OrderItemMapper {
    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);
    OrderItemDto toOrderItemDto(OrderItem orderItem);
    OrderItem toOrderItem(OrderItemDto orderItemDto);
}