package com.bgsystem.bugtracker.models.HQ.plan;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import java.util.Set;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PlanForm {

    private Long id;

    private String name;

    private Double price;

    private Long userLimit;

    private Double diskLimit;

    private Long maxProjects;

    private Long mainHQ;

    private Set<Long> invoice;

    private Set<Long> business;

}
