package com.openroad.api.user.controller.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.openroad.api.user.controller.dtos.UserCreateDTO;
import com.openroad.api.user.controller.dtos.UserDTO;
import com.openroad.api.user.model.User;

@Component
public class UserMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public UserDTO toAdminDTO(User admin) {
        return MODEL_MAPPER.map(admin, UserDTO.class);
    }

    public User toAdminCreate(UserCreateDTO dto) {
        return MODEL_MAPPER.map(dto, User.class);
    }

}
