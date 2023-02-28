package com.ed.app.service;

public class StockExistException extends Exception {
    public StockExistException(String message) {
        super(message);
    }
}
