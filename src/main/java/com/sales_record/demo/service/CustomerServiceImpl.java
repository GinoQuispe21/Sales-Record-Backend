package com.sales_record.demo.service;

import com.sales_record.demo.exception.ResourceNotFoundException;
import com.sales_record.demo.model.Customer;
import com.sales_record.demo.repository.AdminRepository;
import com.sales_record.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public ResponseEntity<?> deleteCustomer(Long adminId, Long customerId) {
        return customerRepository.findByIdAndAdminId(customerId, adminId).map(customer ->{
            customerRepository.delete(customer);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(" Customer not found with Id " + customerId + " and AdminId " + adminId));
    }

    @Override
    public Customer updateCustomer(Long adminId, Long customerId, Customer customerDetail) {
        if(!adminRepository.existsById(adminId))
            throw new ResourceNotFoundException("Admin", "Id", adminId);

        return customerRepository.findById(customerId).map(customer -> {
            customer.setCustomerName(customerDetail.getCustomerName());
            customer.setCustomerLastName(customerDetail.getCustomerLastName());
            customer.setInfo(customerDetail.getInfo());
            customer.setDni(customerDetail.getDni());
            customer.setDepartment(customerDetail.getDepartment());
            customer.setDistrict(customerDetail.getDistrict());
            customer.setAddress(customerDetail.getAddress());
            customer.setPhoneNumber(customerDetail.getPhoneNumber());
            customer.setState(customerDetail.getState());
            return customerRepository.save(customer);
        }).orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", customerId));
    }

    @Override
    public Customer createCustomer(Long adminId, Customer customer) {
        return adminRepository.findById(adminId).map(admin -> {
            customer.setAdmin(admin);
            return customerRepository.save(customer);
        }).orElseThrow(() -> new ResourceNotFoundException("Admin", "Id", adminId));
    }

    @Override
    public Customer getCustomerByIdAndAdminId(Long adminId, Long customerId) {
        return customerRepository.findByIdAndAdminId(customerId, adminId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer not found with Id" + customerId + "and AdminId " + adminId
                ));
    }

    @Override
    public Page<Customer> getAllCustomersByUserId(Long adminId, Pageable pageable) {
        return customerRepository.findByAdminId(adminId, pageable);
    }
}
