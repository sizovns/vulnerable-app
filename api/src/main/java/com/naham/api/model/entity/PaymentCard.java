package com.naham.api.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Table(name = "payment_cards", uniqueConstraints = {@UniqueConstraint(columnNames = "number")})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;
    @Column(nullable = false)
    String number;
    @Column(nullable = false)
    String holder;
    @Column(nullable = false)
    LocalDate expDate;
    @Column(nullable = false)
    String cvc;
}
