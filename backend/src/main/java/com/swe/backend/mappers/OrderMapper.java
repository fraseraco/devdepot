package com.swe.backend.mappers;

import com.swe.backend.dtos.OrderDto;
import com.swe.backend.entity.Order;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring", uses = { UserMapper.class, PaymentMapper.class, OrderItemMapper.class })
public interface OrderMapper {

    @Mapping(source = "user", target = "user")
    @Mapping(source = "transaction", target = "transaction")
    OrderDto toOrderDto(Order order);

    @InheritInverseConfiguration
    @Mapping(target = "user.role", ignore = true)
    @Mapping(source = "orderItems", target = "orderItems")
    @Mapping(target = "transaction.orderId", ignore = true) // 👈 quiet down nested payment.orderId warning
    Order toOrder(OrderDto orderDto);
}