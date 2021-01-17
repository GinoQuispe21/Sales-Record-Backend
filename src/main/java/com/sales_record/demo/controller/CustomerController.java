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

    @GetMapping("/customers")
    public Page<CustomerResource> getAllCustomers(Pageable pageable) {
        Page<Customer> customerPage = customerService.getAllCustomers(pageable);
        List<CustomerResource> resources = customerPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/customers/{id}")
    public CustomerResource getCustomerById(@PathVariable(name = "id") Long customerId) {
        return convertToResource(customerService.getCustomerById(customerId));
    }

    @PostMapping("/customers")
    public CustomerResource createCustomer(@Valid @RequestBody SaveCustomerResource resource)  {
        Customer customer = convertToEntity(resource);
        return convertToResource(customerService.createCustomer(customer));
    }

    @PutMapping("/customers/{id}")
    public CustomerResource updateCustomer(@PathVariable(name = "id") Long customerId, @Valid @RequestBody SaveCustomerResource resource) {
        Customer customer = convertToEntity(resource);
        return convertToResource(customerService.updateCustomer(customerId, customer));
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable(name = "id") Long customerId) {
        return customerService.deleteCustomer(customerId);
    }

    private Customer convertToEntity(SaveCustomerResource resource) { return mapper.map(resource, Customer.class); }

    private CustomerResource convertToResource(Customer entity) { return mapper.map(entity, CustomerResource.class); }
}
