package com.sales_record.demo.controller;

import com.sales_record.demo.model.Customer;
import com.sales_record.demo.resource.CustomerResource;
import com.sales_record.demo.resource.SaveCustomerResource;
import com.sales_record.demo.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/admins/{adminId}/customers")
    public Page<CustomerResource> getAllCustomersByAdminId(@PathVariable(name = "adminId") Long adminId, Pageable pageable) {
        Page<Customer> customerPage = customerService.getAllCustomersByUserId(adminId, pageable);
        List<CustomerResource> resources = customerPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/admins/{adminId}/customers/{customerId}")
    public CustomerResource getCustomerByIdAndAdminId(@PathVariable(name = "adminId") Long adminId, @PathVariable(name = "customerId") Long customerId) {
        return convertToResource(customerService.getCustomerByIdAndAdminId(adminId, customerId));
    }

    @PostMapping("/admins/{adminId}/customers")
    public CustomerResource createCustomer(@PathVariable(name = "adminId") Long adminId, @Valid @RequestBody SaveCustomerResource resource) {
        return convertToResource(customerService.createCustomer(adminId, convertToEntity(resource)));
    }

    @PutMapping("/admins/{adminId}/customers/{customerId}")
    public CustomerResource updateCustomer(@PathVariable(name = "adminId") Long adminId, @PathVariable(name = "customerId") Long customerId, @Valid @RequestBody SaveCustomerResource resource) {
        return convertToResource(customerService.updateCustomer(adminId, customerId, convertToEntity(resource)));
    }

    @DeleteMapping("/admins/{adminId}/customers/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable(name = "adminId") Long adminId, @PathVariable(name = "customerId") Long customerId) {
        return customerService.deleteCustomer(adminId, customerId);
    }

    private Customer convertToEntity(SaveCustomerResource resource) { return mapper.map(resource, Customer.class); }

    private CustomerResource convertToResource(Customer entity) { return mapper.map(entity, CustomerResource.class); }
}
