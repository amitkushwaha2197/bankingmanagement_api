package com.bankingmanagement.service;

import com.bankingmanagement.entity.Customer;
import com.bankingmanagement.exception.CustomerDetailsNotFoundException;
import com.bankingmanagement.model.CustomerTO;
import com.bankingmanagement.repository.CustomerRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Log4j2
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    public CustomerRepository customerRepository;

    @Override
    public List<CustomerTO> getAllCustomers() throws CustomerDetailsNotFoundException {
        log.info("CustomerServiceImpl.getAllCustomers: Fetching all customer details");
        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty()) {
            log.info("CustomerServiceImpl.getAllCustomers: No customer details found");
            throw new CustomerDetailsNotFoundException("No customer details found");
        }

        return customers.stream()
                .map(customer -> new CustomerTO(
                        customer.getCustomerId(),
                        customer.getCustomerName(),
                        customer.getCustomerPhone(),
                        customer.getCustomerAddress()))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerTO getCustomerById(int id) throws CustomerDetailsNotFoundException {
        log.info("CustomerServiceImpl.getCustomerById: Fetching customer details for ID: {}", id);
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerDetailsNotFoundException("Customer not found with ID: " + id));

        return new CustomerTO(
                customer.getCustomerId(),
                customer.getCustomerName(),
                customer.getCustomerPhone(),
                customer.getCustomerAddress());
    }

    @Override
    public CustomerTO getCustomerByName(String name) throws CustomerDetailsNotFoundException {
        log.info("CustomerServiceImpl.getCustomerByName: Fetching customer details for name: {}", name);
        Customer customer = customerRepository.findByCustomerName(name);
        if (Objects.isNull(customer)) {
            log.info("CustomerServiceImpl.getCustomerByName: No customer found with name: {}", name);
            throw new CustomerDetailsNotFoundException("Customer not found with name: " + name);
        }

        return new CustomerTO(
                customer.getCustomerId(),
                customer.getCustomerName(),
                customer.getCustomerPhone(),
                customer.getCustomerAddress());
    }
}
