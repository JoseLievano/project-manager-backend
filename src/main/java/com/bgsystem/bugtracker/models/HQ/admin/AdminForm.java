package com.bgsystem.bugtracker.models.HQ.admin;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AdminForm {

    private Long id;

    private String firstName;

    private String lastName;

    @NotBlank
    private String username;

    private String password;

    private String email;

}
