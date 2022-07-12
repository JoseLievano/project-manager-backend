package com.bgsystem.bugtracker.models.HQ.admin;

import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AdminMiniDTO {

    private Long id;

    private String firstName;

    private String email;

    private Set<String> roles;

    private String username;

}
