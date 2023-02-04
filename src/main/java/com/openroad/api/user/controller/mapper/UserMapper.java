package com.openroad.api.user.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.openroad.api.user.controller.dtos.UserCreateDTO;
import com.openroad.api.user.controller.dtos.UserDTO;
import com.openroad.api.user.model.User;

/**
 * Classe para converter usuário para usuário DTO e também como o contrario
 */
@Component
public class UserMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    /**
     * @param user
     * @return Retorna um usuário DTO
     */
    public UserDTO toUserDTO(User user) {
        return MODEL_MAPPER.map(user, UserDTO.class);
    }

    /**
     * @param userList
     * @return Retorna uma lista de usuários DTOs
     */
    public List<UserDTO> toUserDTOList(List<User> userList) {
        return userList.stream().map(this::toUserDTO).collect(Collectors.toList());
    }

    /**
     * @param dto
     * @return Retorna um Usuário depois de fazer a conversão
     */
    public User toUserCreate(UserCreateDTO dto) {
        return MODEL_MAPPER.map(dto, User.class);
    }

}
