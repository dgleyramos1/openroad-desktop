package com.openroad.api.catalog.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openroad.api.catalog.order.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

}
