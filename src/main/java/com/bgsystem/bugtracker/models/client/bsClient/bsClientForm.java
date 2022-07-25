package com.bgsystem.bugtracker.models.client.bsClient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.Set;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class bsClientForm {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private Boolean isActive;

    private Date dateCreated;

    private Date lastLoginDate;

    private Set<Long> businesses;

}
