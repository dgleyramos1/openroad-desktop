package com.openroad.api.user.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.openroad.api.user.controller.dtos.UserCreateDTO;
import com.openroad.api.user.controller.dtos.UserDTO;
import com.openroad.api.user.controller.mapper.UserMapper;
import com.openroad.api.user.model.User;
import com.openroad.api.user.service.UserService;

/**
 * Controlador de rotas e de serviços do nosso usuário
 */
@Controller
@RequestMapping("/users")
public class AdminController {

    private final UserService userService;
    private final UserMapper userMapper;

    public AdminController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    /**
     * @return Retorna lista com todos os usuários salvos no banco de dados
     */
    public List<UserDTO> findAll() {
        List<User> users = userService.findAll();
        List<UserDTO> result = userMapper.toUserDTOList(users);
        return result;
    }

    /**
     * @param id
     * @return Busca usuário pelo ID
     */
    public ResponseEntity<UserDTO> findById(@PathVariable("id") String id) {
        User user = userService.findById(id);
        UserDTO result = userMapper.toUserDTO(user);
        return ResponseEntity.ok(result);
    }

    /**
     * @param dto
     * @return Cria novo usuário e salva no banco de dados depois retorna um DTO com
     *         os dados desse usuário
     */
    public UserDTO create(@RequestBody UserCreateDTO dto) {
        User userCreate = userMapper.toUserCreate(dto);
        User user = userService.create(userCreate);
        UserDTO result = userMapper.toUserDTO(user);
        return result;
    }

    /**
     * @param id
     * @return Deleta usuário do banco de dados
     */
    public ResponseEntity<UserDTO> delete(@PathVariable("id") String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * @param id
     * @param dto
     * @return Atualiza usuário e retorna um DTO com os dados a serem mostrados
     */
    public ResponseEntity<UserDTO> update(@PathVariable("id") String id, @RequestBody UserCreateDTO dto) {
        User userCreate = userMapper.toUserCreate(dto);
        User user = userService.update(id, userCreate);
        UserDTO result = userMapper.toUserDTO(user);
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    public User findByUsername(String username) {
        User user = userService.findByUsername(username);
        return user;
    }

}
