package com.openroad.api.controller.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.openroad.api.controller.dtos.AdminDTO;
import com.openroad.api.model.Admin;

@Component
public class AdminMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public Admin toAdminCreate(AdminDTO dto) {
        return MODEL_MAPPER.map(dto, Admin.class);
    }

}
