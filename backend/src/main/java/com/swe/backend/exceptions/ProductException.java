package com.swe.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ProductException extends RuntimeException {
    public ProductException(Long id) {
        super("Something went wrong when trying to find product with id: " + id);
    }

    public ProductException() {
        super("Something went wrong when trying to find products.");
    }
}