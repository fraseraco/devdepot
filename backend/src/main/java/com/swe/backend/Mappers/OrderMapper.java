package com.swe.backend.Mappers;

import com.swe.backend.DTOs.OrderDto;
import com.swe.backend.Entity.Order;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    @Mapping(source = "customer", target = "customer")
    OrderDto toOrderDto(Order order);

    @InheritInverseConfiguration
    @Mapping(target = "customer.role", ignore = true)
    Order toOrder(OrderDto orderDto);
}