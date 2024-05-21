package com.bgsystem.bugtracker.shared.models.user;

import lombok.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private Set<String> roles;

}
