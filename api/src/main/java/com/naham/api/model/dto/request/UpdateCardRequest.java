package com.naham.api.model.dto.request;

import com.naham.api.model.validation.CardNumber;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCardRequest {
    long id;
    String name;
    @CardNumber
    String number;
    String holder;
    LocalDate expDate;
    String cvc;
}
