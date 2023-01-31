package com.openroad.api.catalog.category.service;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openroad.api.catalog.category.model.Category;
import com.openroad.api.catalog.category.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository repository;
    private final PasswordEncoder passwordEncoder;

    public CategoryService(CategoryRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Category create(Category categoryCreate) {
        categoryCreate.setId(getUuid());
        categoryCreate.setCreated_at(LocalDateTime.now());
        repository.save(categoryCreate);
        return categoryCreate;
    }

    private String getUuid() {
        return UUID.randomUUID().toString();
    }
}
