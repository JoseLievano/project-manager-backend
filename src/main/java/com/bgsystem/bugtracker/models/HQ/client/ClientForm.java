package com.bgsystem.bugtracker.models.HQ.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Set;

@Data
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class ClientForm {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    @NotBlank
    private String username;

    private String password;

    private Boolean isActive;

    private Date dateCreated;

    private Date lastLoginDate;

    private Long mainHQ;

    private Set<Long> business;

    private Set<Long> invoice;

}
