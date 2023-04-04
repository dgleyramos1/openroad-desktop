package com.openroad.api.catalog.product.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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
@Controller
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

    public void create(String category_id, ProductCreateDTO dto) {
        Category category = categoryService.findById(category_id);
        Product productCreate = mapper.toProductCreateDTO(dto);
        productCreate.setCategory(category);
        mapper.toProductDTO(service.create(productCreate));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        List<Product> list = service.findAll();
        List<ProductDTO> result = mapper.toProductListDTO(list);
        return ResponseEntity.ok(result);
    }

    public List<ProductDTO> listProducts() {
        return mapper.toProductListDTO(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable String id) {
        Product product = service.findById(id);
        ProductDTO result = mapper.toProductDTO(product);
        return ResponseEntity.ok(result);
    }

    public ProductDTO getProduct(String item_id) {
        return mapper.toProductDTO(service.getProductByItemId(item_id));
    }

    @GetMapping("/category/{category_id}")
    public ResponseEntity<List<ProductDTO>> findByCategoryId(@PathVariable String category_id) {
        List<Product> list = service.findByCategoryId(category_id);
        List<ProductDTO> result = mapper.toProductListDTO(list);
        return ResponseEntity.ok(result);
    }

    public void delete(String id) {
        service.delete(id);
    }

    public void update(String id, String category_id, ProductDTO dto) {
        Category category = categoryService.findById(category_id);
        Product productUpdate = mapper.toProduct(dto);
        Product product = service.update(id, productUpdate);
        product.setCategory(category);
        mapper.toProductDTO(product);
    }
}
