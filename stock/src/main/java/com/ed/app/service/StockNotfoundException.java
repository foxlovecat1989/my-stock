package com.ed.app.service;

public class StockNotfoundException extends Exception {
    public StockNotfoundException(String message) {
        super(message);
    }
}
