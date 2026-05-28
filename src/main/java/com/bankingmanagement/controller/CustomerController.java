package com.bankingmanagement.controller;

import com.bankingmanagement.exception.CustomerDetailsNotFoundException;
import com.bankingmanagement.model.CustomerTO;
import com.bankingmanagement.service.CustomerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    public CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerTO>> getAllCustomers() throws CustomerDetailsNotFoundException {
        log.info("CustomerController.getAllCustomers: Fetching all customers");
        List<CustomerTO> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerTO> getCustomerById(@PathVariable("id") int id)
            throws CustomerDetailsNotFoundException {
        log.info("CustomerController.getCustomerById: Fetching customer with ID: {}", id);
        CustomerTO customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/name/")
    public ResponseEntity<CustomerTO> getCustomerByName(@RequestParam("name") String name)
            throws CustomerDetailsNotFoundException {
        log.info("CustomerController.getCustomerByName: Fetching customer with name: {}", name);
        CustomerTO customer = customerService.getCustomerByName(name);
        return ResponseEntity.ok(customer);
    }
}
