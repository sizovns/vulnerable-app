package com.naham.api.model.dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    String username;
    String password;
    boolean enabled = true;
    boolean isAdmin = false; // MassAssignment
}
