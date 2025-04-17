package com.swe.backend.Mappers;

import com.swe.backend.DTOs.OrderItemDto;
import com.swe.backend.DTOs.ProductDto;
import com.swe.backend.Entity.OrderItem;
import com.swe.backend.Entity.OrderItemId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.io.Serializable;
import java.math.BigDecimal;


@Mapper
public interface OrderItemMapper {
    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);
    OrderItemDto toOrderItemDto(OrderItem orderItem);
    OrderItem toOrderItem(OrderItemDto orderItemDto);
}