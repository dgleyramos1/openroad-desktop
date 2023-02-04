package com.openroad.api.catalog.item.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.openroad.api.catalog.item.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

    @Query(value = "SELECT * FROM items WHERE status=true AND order_id=:id", nativeQuery = true)
    public List<Item> findAllStatusTrue(@Param(value = "id") String id);
}
