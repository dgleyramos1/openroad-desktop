package com.openroad.api.controller.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.openroad.api.controller.dtos.AdminCreateDTO;
import com.openroad.api.controller.dtos.AdminDTO;
import com.openroad.api.model.Admin;

@Component
public class AdminMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public AdminDTO toAdminDTO(Admin admin) {
        return MODEL_MAPPER.map(admin, AdminDTO.class);
    }

    public Admin toAdminCreate(AdminCreateDTO dto) {
        return MODEL_MAPPER.map(dto, Admin.class);
    }

}
