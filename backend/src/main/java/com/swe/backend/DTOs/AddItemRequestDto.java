package com.swe.backend.DTOs;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddItemRequestDto implements Serializable {
    Long ProductId;
    int Quantity;
}
