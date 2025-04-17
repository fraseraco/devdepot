package com.swe.backend.Mappers;

import com.swe.backend.DTOs.OrderDto;
import com.swe.backend.DTOs.UserDto;
import com.swe.backend.Entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;


@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    OrderDto toOrderDto(Order order);
    Order toOrder(OrderDto orderDto);
}