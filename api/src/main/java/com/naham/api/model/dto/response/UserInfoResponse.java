package com.naham.api.model.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse {
    long id;
    String username;
    boolean enabled;
    Set<RoleResponse> roles = new HashSet<>();
}
