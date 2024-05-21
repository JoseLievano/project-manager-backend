package com.bgsystem.bugtracker.models.HQ.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeForm {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String password;

}
