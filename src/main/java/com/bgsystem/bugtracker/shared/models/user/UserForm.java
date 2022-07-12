package com.bgsystem.bugtracker.shared.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {

    private Long id;

    private String firstName;

    private String lastName;

    @NotBlank
    private String username;

    private String password;

    private String email;

    private Set<String> roles;

}
