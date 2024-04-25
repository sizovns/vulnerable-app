package com.naham.bff.mapper;

import com.naham.bff.model.dto.response.ProductResponse;
import com.naham.bff.model.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse mapProductResponse(Product source);
}
