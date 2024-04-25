package com.naham.bff.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;

import java.util.*;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bucket {
    @Id
    long id;
    long userId;
    List<Product> products = new ArrayList<>();
}
