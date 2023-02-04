package com.openroad.api.catalog.item.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.openroad.api.catalog.item.controller.dtos.ItemCreateDTO;
import com.openroad.api.catalog.item.controller.dtos.ItemDTO;
import com.openroad.api.catalog.item.model.Item;

@Component
public class ItemMapper {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public ItemDTO toItemDTO(Item item) {
        return MODEL_MAPPER.map(item, ItemDTO.class);
    }

    public List<ItemDTO> toItemListDTO(List<Item> list) {
        return list.stream().map(this::toItemDTO).collect(Collectors.toList());
    }

    public Item toItemCreateDTO(ItemCreateDTO dto) {
        return MODEL_MAPPER.map(dto, Item.class);
    }
}
