package com.swe.backend.Mappers;

import com.swe.backend.DTOs.PaymentDto;
import com.swe.backend.Entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);
    PaymentDto toPaymentDto(Payment payment);
    Payment toPayment(PaymentDto paymentDto);
}