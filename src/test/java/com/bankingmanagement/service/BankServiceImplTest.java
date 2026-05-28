package com.bankingmanagement.service;

import com.bankingmanagement.entity.Bank;
import com.bankingmanagement.entity.Branch;
import com.bankingmanagement.exception.BankDetailsNotFoundException;
import com.bankingmanagement.model.BankTO;
import com.bankingmanagement.model.BranchTO;
import com.bankingmanagement.repository.BankRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BankServiceImplTest {

    @Mock
    public BankRepository bankRepository;

    @InjectMocks
    public BankServiceImpl bankService;

    @Test
    public void getAllBanks_whenBanksExist_thenReturnBankList() throws BankDetailsNotFoundException {
        List<Bank> banks = new ArrayList<>();
        Bank bank1 = new Bank();
        bank1.setBankCode(1);
        bank1.setBankName("Bank A");
        bank1.setBankAddress("Address A");
        Bank bank2 = new Bank();
        bank2.setBankCode(2);
        bank2.setBankName("Bank B");
        bank2.setBankAddress("Address B");

        banks.add(bank1);
        banks.add(bank2);

        when(bankRepository.findAll()).thenReturn(banks);

        List<BankTO> bankTOS= bankService.getAllBanks();
        assertEquals(2, bankTOS.size());
    }

    @Test
    public void getAllBanks_whenBranchExist_thenReturnBankList() throws BankDetailsNotFoundException {
        List<Bank> banks = new ArrayList<>();
        Bank bank1 = new Bank();
        bank1.setBankCode(1);
        bank1.setBankName("Bank A");
        bank1.setBankAddress("Address A");

        Branch branch1 = new Branch();
        branch1.setBranchId(101);
        branch1.setBranchName("Branch A1");
        branch1.setBranchAddress("Branch Address A1");
        Set<Branch> branches1 = new HashSet<>();
        branches1.add(branch1);
        bank1.setBranchSet(branches1);

        Bank bank2 = new Bank();
        bank2.setBankCode(2);
        bank2.setBankName("Bank B");
        bank2.setBankAddress("Address B");

        banks.add(bank1);
        banks.add(bank2);

        when(bankRepository.findAll()).thenReturn(banks);

        List<BankTO> bankTOS= bankService.getAllBanks();
        assertEquals(2, bankTOS.size());
    }

    @Test
    public void getAllBanks_whenNoBanksExist_thenThrowException() {
        when(bankRepository.findAll()).thenReturn(null);

        assertThrows(NullPointerException.class , ()-> bankService.getAllBanks());
    }

    @Test
    public void getAllBanks_whenNoBanksExist_thenThrowBankDetailsException() {
        when(bankRepository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(BankDetailsNotFoundException.class , ()-> bankService.getAllBanks());
    }

    @Test
    public void getBankByCode_whenBankExists_thenReturnBankTO() throws BankDetailsNotFoundException {
        List<Bank> banks = new ArrayList<>();
        Bank bank1 = new Bank();
        bank1.setBankCode(1);
        bank1.setBankName("Bank A");
        bank1.setBankAddress("Address A");
        Bank bank2 = new Bank();
        bank2.setBankCode(2);
        bank2.setBankName("Bank B");
        bank2.setBankAddress("Address B");

        banks.add(bank1);
        banks.add(bank2);

        when(bankRepository.findById(1)).thenReturn(java.util.Optional.of(bank1));

        BankTO bankTOS = bankService.getBankByCode(1);
        assertEquals(1, bankTOS.bankCode());
    }

    @Test
    public void getBankByCode_whenBankDoesNotExist_thenThrowException() {
        when(bankRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(BankDetailsNotFoundException.class, () -> bankService.getBankByCode(99));
    }

    @Test
    public void getBankByCode_whenBankHasNullBranchSet_thenReturnBankTOWithEmptyBranchList() throws BankDetailsNotFoundException {
        Bank bank = new Bank();
        bank.setBankCode(2);
        bank.setBankName("No Branch Bank");
        bank.setBankAddress("No Branch Address");
        bank.setBranchSet(null);

        when(bankRepository.findById(2)).thenReturn(Optional.of(bank));

        BankTO result = bankService.getBankByCode(2);

        assertEquals(2, result.bankCode());
        assertEquals("No Branch Bank", result.bankName());
        assertEquals("No Branch Address", result.bankAddress());
        assertTrue(result.branchList().isEmpty());
    }

    // Test: getBankByCode returns correct BranchTO mapping
    @Test
    public void getBankByCode_whenBankHasBranches_thenReturnCorrectBranchTOList() throws BankDetailsNotFoundException {
        Bank bank = new Bank();
        bank.setBankCode(10);
        bank.setBankName("Test Bank");
        bank.setBankAddress("Test Address");

        Branch branch = new Branch();
        branch.setBranchId(1001);
        branch.setBranchName("Main Branch");
        branch.setBranchAddress("Main Address");
        Set<Branch> branches = new HashSet<>();
        branches.add(branch);
        bank.setBranchSet(branches);

        when(bankRepository.findById(10)).thenReturn(Optional.of(bank));

        BankTO result = bankService.getBankByCode(10);

        assertEquals(1, result.branchList().size());
        BranchTO branchTO = result.branchList().get(0);
        assertEquals(1001, branchTO.branchId());
        assertEquals("Main Branch", branchTO.branchName());
        assertEquals("Main Address", branchTO.branchAddress());
        assertEquals(10, branchTO.bankCode());
    }

    // Test: getBankByCode returns empty branch list when branch set is empty
    @Test
    public void getBankByCode_whenBankHasEmptyBranchSet_thenReturnEmptyBranchList() throws BankDetailsNotFoundException {
        Bank bank = new Bank();
        bank.setBankCode(11);
        bank.setBankName("Empty Branch Bank");
        bank.setBankAddress("No Branch Address");
        bank.setBranchSet(new HashSet<>());

        when(bankRepository.findById(11)).thenReturn(Optional.of(bank));

        BankTO result = bankService.getBankByCode(11);

        assertTrue(result.branchList().isEmpty());
    }

    // Test: getBankByName returns BankTO when bank exists and has branches
    @Test
    public void getBankByName_whenBankExistsWithBranches_thenReturnBankTO() throws BankDetailsNotFoundException {
        Bank bank = new Bank();
        bank.setBankCode(1);
        bank.setBankName("Test Bank");
        bank.setBankAddress("Test Address");

        Branch branch = new Branch();
        branch.setBranchId(100);
        branch.setBranchName("Main Branch");
        branch.setBranchAddress("Main Address");
        Set<Branch> branches = new HashSet<>();
        branches.add(branch);
        bank.setBranchSet(branches);

        when(bankRepository.findByBankName("Test Bank")).thenReturn(bank);

        BankTO result = bankService.getBankByName("Test Bank");

        assertEquals(1, result.bankCode());
        assertEquals("Test Bank", result.bankName());
        assertEquals("Test Address", result.bankAddress());
        assertEquals(1, result.branchList().size());
        BranchTO branchTO = result.branchList().get(0);
        assertEquals(100, branchTO.branchId());
        assertEquals("Main Branch", branchTO.branchName());
        assertEquals("Main Address", branchTO.branchAddress());
        assertEquals(1, branchTO.bankCode());
    }

    // Test: getBankByName returns BankTO with empty branch list when branch set is empty
    @Test
    public void getBankByName_whenBankExistsWithEmptyBranchSet_thenReturnBankTOWithEmptyBranchList() throws BankDetailsNotFoundException {
        Bank bank = new Bank();
        bank.setBankCode(2);
        bank.setBankName("No Branch Bank");
        bank.setBankAddress("No Branch Address");
        bank.setBranchSet(new HashSet<>());

        when(bankRepository.findByBankName("No Branch Bank")).thenReturn(bank);

        BankTO result = bankService.getBankByName("No Branch Bank");

        assertEquals(2, result.bankCode());
        assertEquals("No Branch Bank", result.bankName());
        assertEquals("No Branch Address", result.bankAddress());
        assertTrue(result.branchList().isEmpty());
    }

    // Test: getBankByName returns BankTO with empty branch list when branch set is null
    @Test
    public void getBankByName_whenBankExistsWithNullBranchSet_thenReturnBankTOWithEmptyBranchList() throws BankDetailsNotFoundException {
        Bank bank = new Bank();
        bank.setBankCode(3);
        bank.setBankName("Null Branch Bank");
        bank.setBankAddress("Null Branch Address");
        bank.setBranchSet(null);

        when(bankRepository.findByBankName("Null Branch Bank")).thenReturn(bank);

        BankTO result = bankService.getBankByName("Null Branch Bank");

        assertEquals(3, result.bankCode());
        assertEquals("Null Branch Bank", result.bankName());
        assertEquals("Null Branch Address", result.bankAddress());
        assertTrue(result.branchList().isEmpty());
    }

    // Test: getBankByName throws exception when bank does not exist
    @Test
    public void getBankByName_whenBankDoesNotExist_thenThrowException() {
        when(bankRepository.findByBankName("Unknown Bank")).thenReturn(null);

        assertThrows(BankDetailsNotFoundException.class, () -> bankService.getBankByName("Unknown Bank"));
    }
}