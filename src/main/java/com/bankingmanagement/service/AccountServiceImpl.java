package com.bankingmanagement.service;

import com.bankingmanagement.entity.Account;
import com.bankingmanagement.exception.AccountDetailsNotFoundException;
import com.bankingmanagement.model.AccountTO;
import com.bankingmanagement.repository.AccountRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Log4j2
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    public AccountRepository accountRepository;

    @Override
    public List<AccountTO> getAllAccounts() throws AccountDetailsNotFoundException {
        log.info("AccountServiceImpl.getAllAccounts: Fetching all account details");
        List<Account> accounts = accountRepository.findAll();

        if (accounts.isEmpty()) {
            log.info("No accounts found");
            throw new AccountDetailsNotFoundException("No account details found");
        }

        List<AccountTO> accountTOS = accounts.stream()
                .map(account -> new AccountTO(
                        account.getAccountNumber(),
                        account.getAccountType(),
                        account.getAccountBalance(),
                        account.getCustomer().getCustomerId(),
                        account.getBranch().getBranchId()))
                .collect(Collectors.toList());

        log.info("Successfully fetched all accounts");
        return accountTOS;
    }

    @Override
    public AccountTO getAccountById(int accountNumber) throws AccountDetailsNotFoundException {
        log.info("AccountServiceImpl.getAccountById: Fetching account with ID: {}", accountNumber);

        Account account = accountRepository.findById(accountNumber)
                .orElseThrow(() -> new AccountDetailsNotFoundException("Account not found with ID: " + accountNumber));

        AccountTO accountTO = new AccountTO(
                account.getAccountNumber(),
                account.getAccountType(),
                account.getAccountBalance(),
                account.getCustomer().getCustomerId(),
                account.getBranch().getBranchId());

        log.info("Successfully fetched account with ID: {}", accountNumber);
        return accountTO;
    }

    @Override
    public AccountTO getAccountByType(String type) throws AccountDetailsNotFoundException {
        log.info("AccountServiceImpl.getAccountByType: Fetching account with type: {}", type);

        Account account = accountRepository.findByAccountType(type);
        if (Objects.isNull(account)) {
            log.info("No account found with type: {}", type);
            throw new AccountDetailsNotFoundException("Account not found with type: " + type);
        }

        AccountTO accountTO = new AccountTO(
                account.getAccountNumber(),
                account.getAccountType(),
                account.getAccountBalance(),
                account.getCustomer().getCustomerId(),
                account.getBranch().getBranchId());

        log.info("Successfully fetched account with type: {}", type);
        return accountTO;
    }
}
