package com.naham.bff.mapper;

import com.naham.bff.model.dto.response.OrderResponse;
import com.naham.bff.model.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderResponse mapOrderResponse(Order source);
}
