package com.sales_record.demo.service;

import com.sales_record.demo.exception.ResourceNotFoundException;
import com.sales_record.demo.model.Admin;
import com.sales_record.demo.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public ResponseEntity<?> deleteAdmin(Long adminId) {
        Admin admin = adminRepository.findById(adminId).orElseThrow(() -> new ResourceNotFoundException("Admin", "Id", adminId));
        adminRepository.delete(admin);
        return ResponseEntity.ok().build();
    }

    @Override
    public Admin updateAdmin(Long adminId, Admin adminRequest) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "Id", adminId));
        admin.setName(adminRequest.getName());
        admin.setPhoneNumber(adminRequest.getPhoneNumber());
        admin.setEmail(adminRequest.getEmail());
        return adminRepository.save(admin);
    }

    @Override
    public Admin createAdmin(Admin admin) { return adminRepository.save(admin); }

    @Override
    public Admin getAdminById(Long adminId) {
        return adminRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "Id", adminId));
    }

    @Override
    public Page<Admin> getAllAdmins(Pageable pageable) { return adminRepository.findAll(pageable); }
}
