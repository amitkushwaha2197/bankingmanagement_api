package com.bankingmanagement.exception;

public class BranchDetailsNotFoundException extends Exception{
    public BranchDetailsNotFoundException(String message) {
        super(message);
    }

    public BranchDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
