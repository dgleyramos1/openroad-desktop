package com.openroad.api.catalog.category.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openroad.api.catalog.category.controller.dtos.CategoryCreateDTO;
import com.openroad.api.catalog.category.controller.dtos.CategoryDTO;
import com.openroad.api.catalog.category.controller.mapper.CategoryMapper;
import com.openroad.api.catalog.category.model.Category;
import com.openroad.api.catalog.category.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService service;
    private final CategoryMapper mapper;

    public CategoryController(CategoryService service, CategoryMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryCreateDTO dto) {
        Category categoryCreate = mapper.toCategoryCreateDTO(dto);
        Category category = service.create(categoryCreate);
        CategoryDTO result = mapper.toCategoryDTO(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<Category> list = service.findAll();
        List<CategoryDTO> result = mapper.toCategoryListDTO(list);
        return ResponseEntity.ok(result);
    }

    public List<CategoryDTO> listCategories() {
        return mapper.toCategoryListDTO(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable String id) {
        Category category = service.findById(id);
        CategoryDTO result = mapper.toCategoryDTO(category);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDTO> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable String id, @RequestBody CategoryCreateDTO dto) {
        Category categoryCreate = mapper.toCategoryCreateDTO(dto);
        Category category = service.update(id, categoryCreate);
        CategoryDTO result = mapper.toCategoryDTO(category);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
