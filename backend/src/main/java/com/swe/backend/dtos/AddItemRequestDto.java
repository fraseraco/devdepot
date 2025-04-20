package com.swe.backend.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddItemRequestDto implements Serializable {
    Long ProductId;
    int Quantity;
}
