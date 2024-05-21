package com.bgsystem.bugtracker.models.client.project.bsPrTask;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
public class bsPrTaskForm {

    private Long id;

    private String name;

    private String description;

    private Date created;

    private Date dueDate;

    private Boolean isInternal;

    private Boolean isOverDue;

    private Boolean isDone;

    private Long business;

    private Long project;

    private Long category;

    private Long type;

    private Long priority;

    private Long status;

    private Long invoice;

}
