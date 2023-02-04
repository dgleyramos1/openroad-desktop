package com.openroad.api.catalog.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openroad.api.catalog.item.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

}
