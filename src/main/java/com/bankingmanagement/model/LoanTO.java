package com.bankingmanagement.model;

import java.util.List;

public record LoanTO(
        Integer loanId,
        String loanType,
        Integer loanAmount,
        int customerId,
        int branchId) {
}
