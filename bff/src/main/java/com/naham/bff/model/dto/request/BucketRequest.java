package com.naham.bff.model.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class BucketRequest {
    @Size(min = 1, max = 30)
    Map<Long, Integer> productMap = new HashMap<>();
}
