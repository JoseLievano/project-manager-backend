package com.bgsystem.bugtracker.models.client.bsEmployee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Validated
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class bsEmployeeForm {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    @NotBlank(message = "Username is required")
    private String username;

    private String password;

    private Date dateCreated;

    private Date lastLoginDate;

    private Long business;

}
