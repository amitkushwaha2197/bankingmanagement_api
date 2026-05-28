package com.bankingmanagement.controller;

import com.bankingmanagement.exception.AccountDetailsNotFoundException;
import com.bankingmanagement.model.AccountTO;
import com.bankingmanagement.service.AccountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/accounts")
@Log4j2
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountTO>> getAllAccounts() throws AccountDetailsNotFoundException {
        log.info("Fetching all accounts");
        List<AccountTO> accounts = accountService.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountTO> getAccountById(@PathVariable("id") int id)
            throws AccountDetailsNotFoundException {
        log.info("Fetching account with ID: {}", id);
        AccountTO account = accountService.getAccountById(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping("/type")
    public ResponseEntity<AccountTO> getAccountByType(@RequestParam("type") String type)
            throws AccountDetailsNotFoundException {
        log.info("Fetching account with type: {}", type);
        AccountTO account = accountService.getAccountByType(type);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}
