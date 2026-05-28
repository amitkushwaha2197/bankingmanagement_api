package com.bankingmanagement.service;

import com.bankingmanagement.exception.CustomerDetailsNotFoundException;
import com.bankingmanagement.model.CustomerTO;

import java.util.List;

public interface CustomerService {
    List<CustomerTO> getAllCustomers() throws CustomerDetailsNotFoundException;
    CustomerTO getCustomerById(int id) throws CustomerDetailsNotFoundException;
    CustomerTO getCustomerByName(String name) throws CustomerDetailsNotFoundException;
}
