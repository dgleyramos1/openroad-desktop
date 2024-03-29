package com.openroad.api.catalog.item.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<Item> create(@PathVariable(name = "order_id") String order_id,
            @PathVariable(name = "product_id") String product_id, @RequestBody ItemCreateDTO dto) {
        Order order = orderService.findByID(order_id);
        Product product = productService.findById(product_id);
        Item item = mapper.toItemCreateDTO(dto);
        item.setProduct(product);
        item.setOrder(order);
        Item itemSave = service.create(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemSave);
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<Item> findById(@PathVariable(name = "id") String id) {
        Item item = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ItemDTO> delete(@PathVariable(name = "id") String id) {
        Item item = service.findById(id);
        if (!item.isDraft()) {
            return ResponseEntity.status(404).body(null);
        }
        item.setProduct(null);
        item.setOrder(null);
        Item update = service.update(item);
        service.delete(item);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/kitchen/{id}")
    public ResponseEntity<Item> sendToKitchen(@PathVariable(name = "id") String id) {
        Item item = service.sendToKicthen(id);
        return ResponseEntity.status(HttpStatus.OK).body(item);
    }

    @PutMapping("/delivered/{id}")
    public ResponseEntity<ItemDTO> delivered(@PathVariable String id) {
        Item item = service.delivered(id);
        orderService.setTotalValue(item.getOrder().getId(), item.getOrder().getTotal() + item.getPrice());
        ItemDTO result = mapper.toItemDTO(item);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{order_id}")
    public ResponseEntity<List<ItemDTO>> kitchen(@PathVariable String order_id) {
        List<Item> items = service.kitchen(order_id);
        List<ItemDTO> result = mapper.toItemListDTO(items);
        return ResponseEntity.ok().body(result);
    }

    public List<Item> findAll() {
        return service.findAll();
    }

    public List<Item> getItems(String order_id) {
        return service.findByOrderId(order_id);
    }
}
