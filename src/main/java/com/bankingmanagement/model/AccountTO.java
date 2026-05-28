package com.bankingmanagement.model;


public record AccountTO(
        Integer accountNumber,
        String accountType,
        Integer accountBalance,
        int customerId,
        int branchId) {
}
