package com.swe.backend.mappers;

import com.swe.backend.dtos.OrderItemDto;
import com.swe.backend.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = { ProductMapper.class })  // or whatever mappers you use
public interface OrderItemMapper {
    @Mapping(target = "order", ignore = true)
    OrderItem toOrderItem(OrderItemDto dto);

    OrderItemDto toOrderItemDto(OrderItem entity);
}