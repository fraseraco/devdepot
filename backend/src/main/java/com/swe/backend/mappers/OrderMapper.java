package com.swe.backend.mappers;

import com.swe.backend.dtos.OrderDto;
import com.swe.backend.entity.Order;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    @Mapping(source = "user", target = "user")
    OrderDto toOrderDto(Order order);

    @InheritInverseConfiguration
    @Mapping(target = "user.role", ignore = true)
    @Mapping(source = "orderItems", target = "orderItems")
    Order toOrder(OrderDto orderDto);
}