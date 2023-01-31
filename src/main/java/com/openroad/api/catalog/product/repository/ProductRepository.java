package com.openroad.api.catalog.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openroad.api.catalog.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

}
