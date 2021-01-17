package com.sales_record.demo.service;

import com.sales_record.demo.model.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface AdminService {
    ResponseEntity<?> deleteAdmin(Long adminId);
    Admin updateAdmin(Long adminId, Admin adminRequest);
    Admin createAdmin(Admin admin);
    Admin getAdminById(Long adminId);
    Page<Admin> getAllAdmins(Pageable pageable);
}
