package com.ed.app.service;

import java.io.IOException;

public class ConnectErrorException extends IOException {
    public ConnectErrorException(String message) {
        super(message);
    }
}
