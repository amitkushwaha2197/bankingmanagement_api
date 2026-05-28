package com.bankingmanagement.service;

import com.bankingmanagement.exception.AccountDetailsNotFoundException;
import com.bankingmanagement.model.AccountTO;

import java.util.List;

public interface AccountService {
    List<AccountTO> getAllAccounts() throws AccountDetailsNotFoundException;
    AccountTO getAccountById(int accountNumber) throws AccountDetailsNotFoundException;
    AccountTO getAccountByType(String type) throws AccountDetailsNotFoundException;
}
