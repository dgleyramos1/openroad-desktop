package com.openroad.api.user.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.openroad.api.data.DetailUserData;
import com.openroad.api.user.model.User;
import com.openroad.api.user.repository.UserRepository;

/**
 * Classe que auxilia na autenticação do usuário
 */
@Component
public class DetailUserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public DetailUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.core.userdetails.UserDetailsService#
     * loadUserByUsername(java.lang.String)
     * Retorna os detalhes de login do usuário
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsuario(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("usuário [" + username + "] não encontrado");
        }

        return new DetailUserData(user);
    }

}
