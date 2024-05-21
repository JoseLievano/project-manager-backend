package com.bgsystem.bugtracker.shared.models.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserListDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private Set<String> roles;

}
