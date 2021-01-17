package com.sales_record.demo.service;

import com.sales_record.demo.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    ResponseEntity<?> deleteCustomer(Long customerId);
    Customer updateCustomer(Long customerId, Customer customerRequest);
    Customer createCustomer(Customer customer);
    Customer getCustomerById(Long customerId);
    Page<Customer> getAllCustomers(Pageable pageable);
}
