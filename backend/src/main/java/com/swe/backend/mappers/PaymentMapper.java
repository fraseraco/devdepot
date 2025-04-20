package com.swe.backend.mappers;

import com.swe.backend.dtos.PaymentDto;
import com.swe.backend.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface PaymentMapper {
    @Mapping(target = "orderId", ignore = true) // 👈 silences the warning even if lingering metadata exists
    Payment toPayment(PaymentDto dto);

    PaymentDto toPaymentDto(Payment entity);
}