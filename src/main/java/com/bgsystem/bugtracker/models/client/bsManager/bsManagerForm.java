package com.bgsystem.bugtracker.models.client.bsManager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
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

    @NotBlank(message = "Username is required")
    private String username;

    private String password;

    private Date dateCreated;

    private Date lastLogin;

    private Long business;

}
