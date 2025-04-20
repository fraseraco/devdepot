package com.swe.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class CartException extends RuntimeException {
    public CartException(Long id) {
        super("Cart with id " + id + " not found");
    }
}
