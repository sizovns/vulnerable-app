package com.naham.bff.mapper;

import com.naham.bff.model.dto.response.BucketResponse;
import com.naham.bff.model.entity.Bucket;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
@Setter(onMethod_ = @Autowired)
public abstract class BucketMapper {

    protected ProductMapper productMapper;

    public abstract BucketResponse mapProductResponse(Bucket source);

}
