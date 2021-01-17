package com.sales_record.demo.service;

import com.sales_record.demo.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    ResponseEntity<?> deleteCustomer(Long adminId, Long customerId);
    Customer updateCustomer(Long adminId, Long customerId, Customer customerDetail);
    Customer createCustomer(Long adminId, Customer customer);
    Customer getCustomerByIdAndAdminId(Long adminId, Long customerId);
    Page<Customer> getAllCustomersByUserId(Long adminId, Pageable pageable);
}
