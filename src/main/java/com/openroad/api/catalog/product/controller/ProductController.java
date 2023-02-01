package com.openroad.api.catalog.product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openroad.api.catalog.category.model.Category;
import com.openroad.api.catalog.category.service.CategoryService;
import com.openroad.api.catalog.product.controller.dtos.ProductCreateDTO;
import com.openroad.api.catalog.product.controller.dtos.ProductDTO;
import com.openroad.api.catalog.product.controller.mapper.ProductMapper;
import com.openroad.api.catalog.product.model.Product;
import com.openroad.api.catalog.product.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;
    private final ProductMapper mapper;
    private final CategoryService categoryService;

    public ProductController(ProductService service, ProductMapper mapper, CategoryService categoryService) {
        this.service = service;
        this.mapper = mapper;
        this.categoryService = categoryService;
    }

    @PostMapping("/create/{category_id}")
    public ResponseEntity<ProductDTO> create(@PathVariable(name = "category_id") String category_id,
            @RequestBody ProductCreateDTO dto) {
        Category category = categoryService.findById(category_id);
        Product productCreate = mapper.toProductCreateDTO(dto);
        productCreate.setCategory(category);
        Product product = service.create(productCreate);

        ProductDTO result = mapper.toProductDTO(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
