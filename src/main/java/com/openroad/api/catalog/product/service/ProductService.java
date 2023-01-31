package com.openroad.api.catalog.product.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.openroad.api.catalog.product.model.Product;
import com.openroad.api.catalog.product.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product create(String category_id, Product productCreate) {
        productCreate.setId(getUuid());
        productCreate.setCreated_at(LocalDateTime.now());
        repository.save(productCreate);
        return productCreate;

    }

    private String getUuid() {
        return UUID.randomUUID().toString();
    }

}
