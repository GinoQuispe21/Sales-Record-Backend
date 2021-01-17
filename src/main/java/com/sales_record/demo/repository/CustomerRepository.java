package com.sales_record.demo.repository;

import com.sales_record.demo.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Page<Customer> findByAdminId(Long adminId, Pageable pageable);
    Optional<Customer> findByIdAndAdminId(Long id, Long adminId);
}
