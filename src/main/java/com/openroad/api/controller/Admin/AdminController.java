package com.openroad.api.controller.Admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openroad.api.controller.dtos.AdminCreateDTO;
import com.openroad.api.controller.dtos.AdminDTO;
import com.openroad.api.controller.mapper.AdminMapper;
import com.openroad.api.model.Admin;
import com.openroad.api.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final AdminMapper adminMapper;

    public AdminController(AdminService adminService, AdminMapper adminMapper) {
        this.adminService = adminService;
        this.adminMapper = adminMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<AdminDTO> create(@RequestBody AdminCreateDTO dto) {
        Admin adminCreate = adminMapper.toAdminCreate(dto);
        Admin admin = adminService.create(adminCreate);
        AdminDTO result = adminMapper.toAdminDTO(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
