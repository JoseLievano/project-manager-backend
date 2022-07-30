package com.bgsystem.bugtracker.models.client.bsManager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class bsManagerForm {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private Date dateCreated;

    private Date lastLogin;

    private Long business;

}
