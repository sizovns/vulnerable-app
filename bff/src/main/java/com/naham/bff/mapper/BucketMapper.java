package com.naham.bff.mapper;

import com.naham.bff.model.dto.response.BucketResponse;
import com.naham.bff.model.dto.response.ProductResponse;
import com.naham.bff.model.entity.Bucket;
import com.naham.bff.model.entity.Product;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
@Setter(onMethod_ = @Autowired)
public abstract class BucketMapper {

    protected ProductMapper productMapper;

    @Mapping(target = "products", expression = "java(mapListToMap(source))")
    public abstract BucketResponse mapProductResponse(Bucket source);

    protected Map<ProductResponse, Long> mapListToMap(Bucket source) {
        Map<ProductResponse, Long> productMap = new HashMap<>();
        List<Product> products = source.getProducts();
        if (products == null || products.isEmpty()) {
            return productMap;
        }
        productMap = products.stream()
                .map(product -> productMapper.mapProductResponse(product))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return productMap;

    }
}
