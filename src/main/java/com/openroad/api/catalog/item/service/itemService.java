package com.openroad.api.catalog.item.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.openroad.api.catalog.item.exception.ItemNotFoundException;
import com.openroad.api.catalog.item.model.Item;
import com.openroad.api.catalog.item.repository.ItemRepository;

@Service
public class itemService {

    private final ItemRepository repository;

    public itemService(ItemRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Item create(Item itemCreate) {
        itemCreate.setId(getUuid());
        itemCreate.setCreated_at(LocalDateTime.now());
        itemCreate.setPrice(itemCreate.getProduct().getPrice() * itemCreate.getAmount());
        itemCreate.setDraft(true);
        itemCreate.setStatus(false);
        repository.save(itemCreate);
        return itemCreate;
    }

    @Transactional(readOnly = true)
    public Item findById(String id) {
        return repository.findById(id).orElseThrow(
                () -> new ItemNotFoundException(id));
    }

    @Transactional
    public Item sendToKicthen(String id) {
        Item item = findById(id);
        item.setDraft(false);
        item.setStatus(true);
        item.setUpdated_at(LocalDateTime.now());
        repository.save(item);
        return item;
    }

    @Transactional
    public Item delivered(String id) {
        Item item = findById(id);
        item.setStatus(false);
        item.setUpdated_at(LocalDateTime.now());
        repository.save(item);
        return item;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Item> kitchen(String order_id) {
        return repository.findAllStatusTrue(order_id);
    }

    @Transactional
    public void delete(Item item) {
        repository.delete(item);
    }

    private String getUuid() {
        return UUID.randomUUID().toString();
    }

}
