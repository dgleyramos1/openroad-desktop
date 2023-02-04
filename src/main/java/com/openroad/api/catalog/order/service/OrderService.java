package com.openroad.api.catalog.order.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openroad.api.catalog.order.exception.OrderNotFoundException;
import com.openroad.api.catalog.order.model.Order;
import com.openroad.api.catalog.order.repository.OrderRepository;

@Service
public class OrderService {

    public final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Order create(Order orderCreate) {
        orderCreate.setId(getUuid());
        orderCreate.setStatus(true);
        orderCreate.setCreated_at(LocalDateTime.now());
        orderCreate.setTotal(0.0);
        repository.save(orderCreate);
        return orderCreate;
    }

    @Transactional(readOnly = true)
    public Order findByID(String id) {
        return repository.findById(id).orElseThrow(
                () -> new OrderNotFoundException(id));
    }

    @Transactional
    public Order update(String id, Order orderUpdate) {
        Order order = findByID(id);
        order.setTable(orderUpdate.getTable());
        order.setUpdated_at(LocalDateTime.now());
        repository.save(order);
        return order;

    }

    private String getUuid() {
        return UUID.randomUUID().toString();
    }

}
