package com.swe.backend.exceptions;

public class OutOfStockException extends RuntimeException {
    public OutOfStockException(String productName) {
        super("Product '" + productName + "' is out of stock");
    }

    public OutOfStockException(String productName, Throwable cause) {
        super("Product '" + productName + "' is out of stock", cause);
    }
}
