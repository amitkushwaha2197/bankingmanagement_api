package com.bankingmanagement.service;

import com.bankingmanagement.entity.Branch;
import com.bankingmanagement.exception.BranchDetailsNotFoundException;
import com.bankingmanagement.model.BranchTO;
import com.bankingmanagement.repository.BranchRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Log4j2
@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Override
    public List<BranchTO> getAllBranch() throws BranchDetailsNotFoundException {
        log.info("BranchServiceImpl.getAllBranches: Fetching all branches");
        List<Branch> branches = branchRepository.findAll();
        if (branches.isEmpty()) {
            throw new BranchDetailsNotFoundException("No branches found");
        }

        List<BranchTO> branchTOS = branches.stream()
                .map(branch -> new BranchTO(
                        branch.getBranchId(),
                        branch.getBranchName(),
                        branch.getBranchAddress(),
                        branch.getBank().getBankCode()))
                .collect(Collectors.toList());

        log.info("Successfully fetched all branches");
        return branchTOS;
    }

    @Override
    public BranchTO getBranchById(int branchId) throws BranchDetailsNotFoundException {
        log.info("BranchServiceImpl.getBranchById: Fetching branch with ID: {}", branchId);
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new BranchDetailsNotFoundException("Branch not found with ID: " + branchId));

        return new BranchTO(
                branch.getBranchId(),
                branch.getBranchName(),
                branch.getBranchAddress(),
                branch.getBank().getBankCode());
    }

    @Override
    public BranchTO getBranchByName(String branchName) throws BranchDetailsNotFoundException {
        log.info("BranchServiceImpl.getBranchByName: Fetching branch with name: {}", branchName);
        Branch branch = branchRepository.findByBranchName(branchName);
        if(Objects.isNull(branch)) {
            log.info("BranchServiceImpl.getBranchByName: Branch details not found for name: {}", branchName);
            throw new BranchDetailsNotFoundException("Branch not found with name: " + branchName);
        }

        return new BranchTO(
                branch.getBranchId(),
                branch.getBranchName(),
                branch.getBranchAddress(),
                branch.getBank().getBankCode());
    }
}
