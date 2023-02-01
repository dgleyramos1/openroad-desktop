package com.openroad.api.catalog.product.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.openroad.api.catalog.product.execption.ProductNotFoundException;
import com.openroad.api.catalog.product.model.Product;
import com.openroad.api.catalog.product.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Product create(Product productCreate) {
        productCreate.setId(getUuid());
        productCreate.setCreated_at(LocalDateTime.now());
        repository.save(productCreate);
        System.out.println(productCreate);
        return productCreate;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Product findById(String id) {
        return repository.findById(id).orElseThrow(
                () -> new ProductNotFoundException(id));
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Product> findByCategoryId(String category_id) {
        return repository.findByCategoryID(category_id);
    }

    private String getUuid() {
        return UUID.randomUUID().toString();
    }

}
