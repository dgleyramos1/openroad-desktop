package com.openroad.api.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openroad.api.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    public Optional<User> findByUsername(String username);

}
