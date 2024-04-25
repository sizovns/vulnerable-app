package com.naham.bff.model.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    long id;
    CardResponse paymentCard;
    List<ProductResponse> products = new ArrayList<>();
}
