package com.sales_record.demo.controller;

import com.sales_record.demo.model.Admin;
import com.sales_record.demo.resource.AdminResource;
import com.sales_record.demo.resource.SaveAdminResource;
import com.sales_record.demo.service.AdminService;
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
public class AdminController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AdminService adminService;

    @GetMapping("/admins")
    public Page<AdminResource> getAllAdmins(Pageable pageable) {
        Page<Admin> adminPage = adminService.getAllAdmins(pageable);
        List<AdminResource> resources = adminPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/admins/{id}")
    public AdminResource getAdminById(@PathVariable(name = "id") Long adminId) {
        return convertToResource(adminService.getAdminById(adminId));
    }

    @PostMapping("/admins")
    public AdminResource createAdmin(@Valid @RequestBody SaveAdminResource resource)  {
        Admin admin = convertToEntity(resource);
        return convertToResource(adminService.createAdmin(admin));
    }

    @PutMapping("/admins/{id}")
    public AdminResource updateAdmin(@PathVariable(name = "id") Long adminId, @Valid @RequestBody SaveAdminResource resource) {
        Admin admin = convertToEntity(resource);
        return convertToResource(adminService.updateAdmin(adminId, admin));
    }

    @DeleteMapping("/admins/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable(name = "id") Long adminId) {
        return adminService.deleteAdmin(adminId);
    }

    private Admin convertToEntity(SaveAdminResource resource) { return mapper.map(resource, Admin.class); }

    private AdminResource convertToResource(Admin entity) { return mapper.map(entity, AdminResource.class); }
}
