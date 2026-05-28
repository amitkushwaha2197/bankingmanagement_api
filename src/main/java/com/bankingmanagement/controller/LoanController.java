package com.bankingmanagement.controller;

import com.bankingmanagement.exception.LoanDetailsNotFoundException;
import com.bankingmanagement.model.LoanTO;
import com.bankingmanagement.service.LoanService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping
    public ResponseEntity<List<LoanTO>> getAllLoans() throws LoanDetailsNotFoundException {
        log.info("LoanController.getAllLoans: Fetching all loans");
        List<LoanTO> loanTOS = loanService.getAllLoans();
        return ResponseEntity.ok(loanTOS);
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<LoanTO> getLoanById(@PathVariable("loanId") int loanId)
            throws LoanDetailsNotFoundException {
        log.info("LoanController.getLoanById: Fetching loan with ID: {}", loanId);
        LoanTO loanTO = loanService.getLoanById(loanId);
        return ResponseEntity.ok(loanTO);
    }

    @GetMapping("/type")
    public ResponseEntity<LoanTO> getLoanByType(@RequestParam("type") String type)
            throws LoanDetailsNotFoundException {
        log.info("LoanController.getLoanByType: Fetching loan with type: {}", type);
        LoanTO loanTO = loanService.getLoanByType(type);
        return ResponseEntity.ok(loanTO);
    }
}
