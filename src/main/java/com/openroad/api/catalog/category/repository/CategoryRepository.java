package com.openroad.api.catalog.category.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openroad.api.catalog.category.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    public Optional<Category> findByName(String name);

}
