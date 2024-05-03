package com.naham.bff.mapper;

import com.naham.bff.model.dto.response.BasketResponse;
import com.naham.bff.model.entity.Basket;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
@Setter(onMethod_ = @Autowired)
public abstract class BasketMapper {

    protected ProductMapper productMapper;

    public abstract BasketResponse mapProductResponse(Basket source);

}
