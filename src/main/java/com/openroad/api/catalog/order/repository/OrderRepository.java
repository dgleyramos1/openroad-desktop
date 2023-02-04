package com.openroad.api.catalog.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.openroad.api.catalog.order.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    @Query(value = "SELECT * FROM orders WHERE status=true", nativeQuery = true)
    public List<Order> findAllStatusTrue();
}
