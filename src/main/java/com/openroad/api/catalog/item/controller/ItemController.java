package com.openroad.api.catalog.item.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openroad.api.catalog.item.controller.dtos.ItemCreateDTO;
import com.openroad.api.catalog.item.controller.dtos.ItemDTO;
import com.openroad.api.catalog.item.controller.mapper.ItemMapper;
import com.openroad.api.catalog.item.model.Item;
import com.openroad.api.catalog.item.service.itemService;
import com.openroad.api.catalog.order.model.Order;
import com.openroad.api.catalog.order.service.OrderService;
import com.openroad.api.catalog.product.model.Product;
import com.openroad.api.catalog.product.service.ProductService;

@RestController
@RequestMapping("/items")
public class ItemController {

    public final ItemMapper mapper;
    public final itemService service;
    public final ProductService productService;
    public final OrderService orderService;

    public ItemController(ItemMapper mapper, itemService service, ProductService productService,
            OrderService orderService) {
        this.mapper = mapper;
        this.service = service;
        this.productService = productService;
        this.orderService = orderService;
    }

    @PostMapping("/create/{order_id}/{product_id}")
    public ResponseEntity<ItemDTO> create(@PathVariable(name = "order_id") String order_id,
            @PathVariable(name = "product_id") String product_id, @RequestBody ItemCreateDTO dto) {
        Order order = orderService.findByID(order_id);
        Product product = productService.findById(product_id);
        Item item = mapper.toItemCreateDTO(dto);
        item.setProduct(product);
        item.setOrder(order);
        Item itemSave = service.create(item);
        order.setTotal(order.getTotal() + itemSave.getPrice());
        orderService.update(order_id, order);
        ItemDTO result = mapper.toItemDTO(itemSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> sendToKitchen(@PathVariable(name = "id") String id) {
        Item sendItem = service.sendToKicthen(id);
        ItemDTO result = mapper.toItemDTO(sendItem);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
