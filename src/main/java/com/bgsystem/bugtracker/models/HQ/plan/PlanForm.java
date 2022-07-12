package com.bgsystem.bugtracker.models.HQ.plan;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import java.util.Set;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PlanForm {

    private Long id;

    private String name;

    @Min(0)
    private Double price;

    @Min(1)
    private Long userLimit;

    @Min(1)
    private Double diskLimit;

    @Min(1)
    private Long maxProjects;

    private Long mainHQ;

    private Set<Long> invoice;

    private Set<Long> business;

}
