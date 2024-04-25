package com.naham.bff.model.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardResponse {
    long id;
    String name;
    String number;
    String holder;
    String expDate;
}
