package com.openroad.api.catalog.product.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.openroad.api.catalog.product.controller.dtos.ProductCreateDTO;
import com.openroad.api.catalog.product.controller.dtos.ProductDTO;
import com.openroad.api.catalog.product.model.Product;

@Component
public class ProductMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public ProductDTO toProductDTO(Product product) {
        return MODEL_MAPPER.map(product, ProductDTO.class);
    }

    public List<ProductDTO> toCategoryListDTO(List<Product> list) {
        return list.stream().map(this::toProductDTO).collect(Collectors.toList());
    }

    public Product toCategoryCreateDTO(ProductCreateDTO dto) {
        return MODEL_MAPPER.map(dto, Product.class);
    }
}
