package com.openroad.api.user.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.openroad.api.user.controller.dtos.UserCreateDTO;
import com.openroad.api.user.controller.dtos.UserDTO;
import com.openroad.api.user.model.User;

@Component
public class UserMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public UserDTO toUserDTO(User user) {
        return MODEL_MAPPER.map(user, UserDTO.class);
    }

    public List<UserDTO> toUserDTOList(List<User> userList) {
        return userList.stream().map(this::toUserDTO).collect(Collectors.toList());
    }

    public User toUser(UserDTO dto) {
        return MODEL_MAPPER.map(dto, User.class);
    }

    public User toUserCreate(UserCreateDTO dto) {
        return MODEL_MAPPER.map(dto, User.class);
    }

}
