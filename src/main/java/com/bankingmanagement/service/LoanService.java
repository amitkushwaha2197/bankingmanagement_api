package com.bankingmanagement.service;

import com.bankingmanagement.exception.LoanDetailsNotFoundException;
import com.bankingmanagement.model.LoanTO;

import java.util.List;

public interface LoanService {
    List<LoanTO> getAllLoans() throws LoanDetailsNotFoundException;
    LoanTO getLoanById(int loanId) throws LoanDetailsNotFoundException;
    LoanTO getLoanByType(String type) throws LoanDetailsNotFoundException;
}
