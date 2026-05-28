package com.bankingmanagement.exception;

public class CustomerDetailsNotFoundException extends Exception{
    public CustomerDetailsNotFoundException(String message) {
        super(message);
    }

    public CustomerDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

