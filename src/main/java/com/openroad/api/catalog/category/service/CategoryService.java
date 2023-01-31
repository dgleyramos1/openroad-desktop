package com.openroad.api.catalog.category.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.openroad.api.catalog.category.model.Category;
import com.openroad.api.catalog.category.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Category create(Category categoryCreate) {
        Optional<Category> exist = repository.findByName(categoryCreate.getName());
        if (!exist.isEmpty()) {
            System.out.println("Categoria já exist");
            throw new Error("Category exist!");
        }
        categoryCreate.setId(getUuid());
        categoryCreate.setCreated_at(LocalDateTime.now());
        repository.save(categoryCreate);
        return categoryCreate;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Category> findAll() {
        return repository.findAll();
    }

    private String getUuid() {
        return UUID.randomUUID().toString();
    }
}
