package com.openroad.api.catalog.category.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openroad.api.catalog.category.controller.dtos.CategoryCreateDTO;
import com.openroad.api.catalog.category.controller.dtos.CategoryDTO;
import com.openroad.api.catalog.category.controller.mapper.CategoryMapper;
import com.openroad.api.catalog.category.model.Category;
import com.openroad.api.catalog.category.service.CategoryService;

@RestController
@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService service;
    private final CategoryMapper mapper;

    public CategoryController(CategoryService service, CategoryMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    public void createCategory(String text) {
        CategoryCreateDTO categoryCreateDTO = new CategoryCreateDTO();
        categoryCreateDTO.setName(text);
        Category categoryCreate = mapper.toCategoryCreateDTO(categoryCreateDTO);
        mapper.toCategoryDTO(service.create(categoryCreate));
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

    public CategoryDTO categoryById(String id) {
        return mapper.toCategoryDTO(service.findById(id));
    }

    public void delete(String id) {
        service.delete(id);
    }

    public void update(String id, CategoryDTO dto) {
        Category category = mapper.toCategory(dto);
        service.update(id, category);
    }

}
