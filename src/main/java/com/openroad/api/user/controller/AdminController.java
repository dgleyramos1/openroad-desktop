package com.openroad.api.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openroad.api.user.controller.dtos.UserCreateDTO;
import com.openroad.api.user.controller.dtos.UserDTO;
import com.openroad.api.user.controller.mapper.UserMapper;
import com.openroad.api.user.model.User;
import com.openroad.api.user.service.UserService;

@RestController
@RequestMapping("/users")
public class AdminController {

    private final UserService userService;
    private final UserMapper userMapper;

    public AdminController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> create(@RequestBody UserCreateDTO dto) {
        System.out.println(dto);
        User userCreate = userMapper.toAdminCreate(dto);
        User user = userService.create(userCreate);
        UserDTO result = userMapper.toAdminDTO(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
