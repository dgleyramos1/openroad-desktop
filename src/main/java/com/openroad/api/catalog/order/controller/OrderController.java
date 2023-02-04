package com.openroad.api.catalog.order.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openroad.api.catalog.order.controller.dtos.OrderCreateDTO;
import com.openroad.api.catalog.order.controller.dtos.OrderDTO;
import com.openroad.api.catalog.order.controller.mapper.OrderMapper;
import com.openroad.api.catalog.order.model.Order;
import com.openroad.api.catalog.order.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;
    private final OrderMapper mapper;

    public OrderController(OrderService service, OrderMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping("/create")
    public ResponseEntity<OrderDTO> create(@RequestBody OrderCreateDTO dto) {
        Order orderCreate = mapper.toOrderCreateDTO(dto);
        Order order = service.create(orderCreate);
        OrderDTO result = mapper.toOrderDTO(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll() {
        List<Order> list = service.findAll();
        List<OrderDTO> result = mapper.toOrderListDTO(list);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable String id) {
        Order order = service.findByID(id);
        OrderDTO result = mapper.toOrderDTO(order);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> update(@PathVariable String id, @RequestBody OrderCreateDTO dto) {
        Order orderUpdate = mapper.toOrderCreateDTO(dto);
        Order order = service.update(id, orderUpdate);
        OrderDTO result = mapper.toOrderDTO(order);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
