package com.naham.api.model.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class CardResponse {
    long id;
    String name;
    String number;
    String holder;
    String expDate;
    String cvc;
}
