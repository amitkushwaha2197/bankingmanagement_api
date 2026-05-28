package com.bankingmanagement.service;

import com.bankingmanagement.entity.Loan;
import com.bankingmanagement.exception.LoanDetailsNotFoundException;
import com.bankingmanagement.model.LoanTO;
import com.bankingmanagement.repository.LoanRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Log4j2
@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public List<LoanTO> getAllLoans() throws LoanDetailsNotFoundException {
        log.info("LoanServiceImpl.getAllLoans: Fetching all loan details");
        List<Loan> loans = loanRepository.findAll();

        if (loans.isEmpty()) {
            throw new LoanDetailsNotFoundException("No loans found");
        }

        List<LoanTO> loanTOS = loans.stream()
                .map(loan -> new LoanTO(
                        loan.getLoanId(),
                        loan.getLoanType(),
                        loan.getLoanAmount(),
                        loan.getCustomer().getCustomerId(),
                        loan.getBranch().getBranchId()))
                .collect(Collectors.toList());

        log.info("Successfully fetched {} loans", loanTOS.size());
        return loanTOS;
    }

    @Override
    public LoanTO getLoanById(int loanId) throws LoanDetailsNotFoundException {
        log.info("LoanServiceImpl.getLoanById: Fetching loan with ID: {}", loanId);

        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanDetailsNotFoundException("Loan not found with ID: " + loanId));

        return new LoanTO(
                loan.getLoanId(),
                loan.getLoanType(),
                loan.getLoanAmount(),
                loan.getCustomer().getCustomerId(),
                loan.getBranch().getBranchId());
    }

    @Override
    public LoanTO getLoanByType(String loanType) throws LoanDetailsNotFoundException {
        log.info("LoanServiceImpl.getLoansByLoanType: Fetching loans for loanType: {}", loanType);
        Loan loans = loanRepository.findByLoanType(loanType);

        if (Objects.isNull(loans)) {
            throw new LoanDetailsNotFoundException("No loans found for loanType: " + loanType);
        }

        return new LoanTO(
                loans.getLoanId(),
                loans.getLoanType(),
                loans.getLoanAmount(),
                loans.getCustomer().getCustomerId(),
                loans.getBranch().getBranchId());
    }
}
