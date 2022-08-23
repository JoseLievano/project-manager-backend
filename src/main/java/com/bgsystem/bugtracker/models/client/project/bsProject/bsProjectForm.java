package com.bgsystem.bugtracker.models.client.project.bsProject;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.Set;

@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
public class bsProjectForm {

    private Long id;

    private String name;

    private Boolean isCompleted;

    private Date created;

    private Date dueDate;

    private Long business;

    private Long client;

    private Set<Long> invoices;

    private Set<Long> channels;

    private Set<Long> docsCategories;

    private Set<Long> docs;

}
