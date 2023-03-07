package com.openroad.api.user.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openroad.api.user.controller.dtos.UserDTO;
import com.openroad.api.user.exception.UserNotFoundException;
import com.openroad.api.user.model.User;
import com.openroad.api.user.repository.UserRepository;

/**
 * Classe de serviço
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    /**
     * @return Metodo para criptografar a senha do usuário
     */
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @param userCreate
     * @return Cria um novo usuário e salva ele no banco de dados e retorna ele
     */
    @Transactional
    public User create(User userCreate) {
        Optional<User> userExists = userRepository.findByUsername(userCreate.getUsername());

        if (!userExists.isEmpty()) {
            throw new Error("User exists!");
        }
        userCreate.setId(getUuid());
        userCreate.setPassword(encoder.encode(userCreate.getPassword()));
        userCreate.setCreated_at(LocalDateTime.now());
        userRepository.save(userCreate);
        return userCreate;
    }

    /**
     * @return Faz a listagem de todos os usuários no banco de dados
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * @param id
     * @return Busca usuário pelo ID e retorna o usuário encontrado
     */
    @Transactional(readOnly = true)
    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(id));
    }

    /**
     * @param id
     *           Deleta usuário do banco de dados
     */
    @Transactional
    public void deleteUser(String id) {
        User user = findById(id);
        if (user == null) {
            return;
        }
        userRepository.deleteById(id);
    }

    /**
     * @param id
     * @param userCreate
     * @return Atualiza usuário e retorna ele mesmo
     */
    @Transactional
    public User update(String id, User userCreate) {
        User user = findById(id);
        user.setName(userCreate.getName());
        user.setPassword(encoder.encode(userCreate.getPassword()));
        user.setRole(userCreate.getRole());
        user.setUsername(userCreate.getUsername());
        user.setUpdated_at(LocalDateTime.now());
        return user;
    }

    /**
     * @return Retorna um UUID randomico e único
     */
    private String getUuid() {
        return UUID.randomUUID().toString();
    }

    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

}
