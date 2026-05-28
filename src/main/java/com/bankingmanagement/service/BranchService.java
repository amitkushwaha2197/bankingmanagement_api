package com.bankingmanagement.service;

import com.bankingmanagement.exception.BankDetailsNotFoundException;
import com.bankingmanagement.exception.BranchDetailsNotFoundException;
import com.bankingmanagement.model.BranchTO;

import java.util.List;

public interface BranchService {
    List<BranchTO> getAllBranch() throws BranchDetailsNotFoundException;
    BranchTO getBranchById(int branchId) throws BranchDetailsNotFoundException;
    BranchTO getBranchByName(String branchName) throws BranchDetailsNotFoundException;
}
