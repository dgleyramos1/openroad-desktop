package com.openroad.api.user.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.openroad.api.user.model.User;
import com.openroad.api.user.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Transactional
    public User create(User userCreate) {
        System.out.println(userCreate);
        Optional<User> userExists = userRepository.findByUsername(userCreate.getUsername());

        if (userExists == null) {
            throw new Error("User does not exists!");
        }

        userCreate.setPassword(encoder.encode(userCreate.getPassword()));
        userCreate.setCreated_at(LocalDateTime.now());
        userRepository.save(userCreate);
        return userCreate;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<User> findAlll() {
        return userRepository.findAll();
    }

}
