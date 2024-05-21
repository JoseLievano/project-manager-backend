package com.bgsystem.bugtracker.models.client.bsClient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
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

    @NotBlank(message = "Username is required")
    private String username;

    private String password;

    private Boolean isActive;

    private Date dateCreated;

    private Date lastLoginDate;

    private Long business;

    private Set<Long> projects;

    private Set<Long> invoices;

    private String address;

    private String website;

    private String phone;

    private String country;

    private String companyName;

}
