package com.openroad.api.catalog.category.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.openroad.api.catalog.category.controller.dtos.CategoryCreateDTO;
import com.openroad.api.catalog.category.controller.dtos.CategoryDTO;
import com.openroad.api.catalog.category.model.Category;

@Component
public class CategoryMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public CategoryDTO toCategoryDTO(Category category) {
        return MODEL_MAPPER.map(category, CategoryDTO.class);
    }

    public Category toCategory(CategoryDTO dto) {
        return MODEL_MAPPER.map(dto, Category.class);
    }

    public List<CategoryDTO> toCategoryListDTO(List<Category> list) {
        return list.stream().map(this::toCategoryDTO).collect(Collectors.toList());
    }

    public Category toCategoryCreateDTO(CategoryCreateDTO dto) {
        return MODEL_MAPPER.map(dto, Category.class);
    }
}
