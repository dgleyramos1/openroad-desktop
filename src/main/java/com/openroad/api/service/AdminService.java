package com.openroad.api.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openroad.api.model.Admin;
import com.openroad.api.repository.AdminRepository;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Transactional
    public Admin create(Admin adminCreate) {
        String uuid = getUUID();
        adminCreate.setId(uuid);
        adminCreate.setCreated_at(LocalDateTime.now());
        adminRepository.save(adminCreate);
        return adminCreate;
    }

    // Gerando UUID único
    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
