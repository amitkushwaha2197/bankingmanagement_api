package com.bankingmanagement.controller;

import com.bankingmanagement.exception.BranchDetailsNotFoundException;
import com.bankingmanagement.model.BranchTO;
import com.bankingmanagement.service.BranchService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/v1/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @GetMapping
    public ResponseEntity<List<BranchTO>> getAllBranches() throws BranchDetailsNotFoundException {
        log.info("BranchController.getAllBranches: Fetching all branches");
        List<BranchTO> branchTOS = branchService.getAllBranch();
        return ResponseEntity.ok(branchTOS);
    }

    @GetMapping("/{branchId}")
    public ResponseEntity<BranchTO> getBranchById(@PathVariable("branchId") int branchId)
            throws BranchDetailsNotFoundException {
        log.info("BranchController.getBranchById: Fetching branch with ID: {}", branchId);
        BranchTO branchTO = branchService.getBranchById(branchId);
        return ResponseEntity.ok(branchTO);
    }

    @GetMapping("/name")
    public ResponseEntity<BranchTO> getBranchByName(@RequestParam("branchName") String branchName)
            throws BranchDetailsNotFoundException {
        log.info("BranchController.getBranchByName: Fetching branch with name: {}", branchName);
        BranchTO branchTO = branchService.getBranchByName(branchName);
        log.info("Successfully fetched branch details for branch name: {}", branchName);
        return ResponseEntity.ok(branchTO);
    }
}
