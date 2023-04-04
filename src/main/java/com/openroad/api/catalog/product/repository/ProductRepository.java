package com.openroad.api.catalog.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.openroad.api.catalog.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @Query(value = "SELECT * FROM products WHERE category_id=:category_id", nativeQuery = true)
    public List<Product> findByCategoryID(@Param(value = "category_id") String category_id);

    public List<Product> findAllByAtivoTrue();

    @Query(value = "SELECT * FROM products p LEFT JOIN items i ON p.id = :id)", nativeQuery = true)
    public Product findByItemId(@Param(value = "id") String id);
}
