package com.bgsystem.bugtracker.models.HQ.client;

import lombok.*;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ClientMiniDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Set<String> roles;

    private String username;

    private Boolean isActive;

    private Date dateCreated;

    private Date lastLoginDate;

}
