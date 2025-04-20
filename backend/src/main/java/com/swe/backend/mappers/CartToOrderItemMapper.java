package com.swe.backend.mappers;

import com.swe.backend.entity.CartItem;
import com.swe.backend.entity.Order;
import com.swe.backend.entity.OrderItem;
import com.swe.backend.entity.OrderItemId;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CartToOrderItemMapper {
    @Mapping(target = "id", ignore = true)              // composite key generated later
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "product", source = "product")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "pricePerUnit", source = "product.price")
    OrderItem toOrderItem(CartItem cartItem);

    /**
     * After‑mapping hook to build the composite key once all fields are set.
     */
    @AfterMapping
    default void link(@MappingTarget OrderItem oi,
                      CartItem ci,
                      @Context Order order) {
        oi.setOrder(order);
        oi.setId(new OrderItemId(order.getId(), ci.getProduct().getId()));
    }
}
