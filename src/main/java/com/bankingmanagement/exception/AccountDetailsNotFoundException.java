package com.bankingmanagement.exception;

public class AccountDetailsNotFoundException extends Exception{
    public AccountDetailsNotFoundException(String message) {
        super(message);
    }

    public AccountDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
