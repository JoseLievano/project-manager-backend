package com.bgsystem.bugtracker.models.client.bsPriority;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class bsPriorityForm {

    private Long id;

    private String name;

    private Integer priorityOrder;

    private Long business;

    private Set<Long> tasks;

}
