package com.bgsystem.bugtracker.models.client.project.bsProject;

import lombok.*;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
public class bsProjectForm {

    private Long id;

    private String name;

    private Long business;

    private Long client;

}
