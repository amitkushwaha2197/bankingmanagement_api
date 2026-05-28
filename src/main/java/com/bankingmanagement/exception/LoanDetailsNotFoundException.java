package com.bankingmanagement.exception;

public class LoanDetailsNotFoundException extends Exception{
    public LoanDetailsNotFoundException(String message) {
        super(message);
    }

    public LoanDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

