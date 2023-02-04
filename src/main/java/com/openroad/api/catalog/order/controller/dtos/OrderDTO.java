package com.openroad.api.catalog.order.controller.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.openroad.api.catalog.item.controller.dtos.ItemDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    private String id;
    private int table;
    private Boolean status;
    private Float total;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime created_at;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime updated_at;
    private List<ItemDTO> items;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

}
