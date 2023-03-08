package com.openroad.api.catalog.category.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.openroad.api.catalog.category.exception.CategoryNotFoundException;
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
            throw new Error("Category exist!");
        }
        categoryCreate.setId(getUuid());
        categoryCreate.setCreated_at(LocalDate.now());
        repository.save(categoryCreate);
        return categoryCreate;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Category findById(String id) {
        return repository.findById(id).orElseThrow(
                () -> new CategoryNotFoundException(id));
    }

    @Transactional
    public void delete(String id) {
        Category category = findById(id);
        if (category == null) {
            return;
        }
        repository.deleteById(id);
    }

    @Transactional
    public Category update(String id, Category categoryUpdate) {
        Category category = findById(id);
        category.setName(categoryUpdate.getName());
        category.setUpdated_at(LocalDate.now());
        repository.save(category);
        return category;
    }

    private String getUuid() {
        return UUID.randomUUID().toString();
    }
}
