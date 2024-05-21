package com.bgsystem.bugtracker.models.HQ.plan;

import com.bgsystem.bugtracker.models.HQ.mainHQ.MainHQMiniDTO;
import lombok.*;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PlanMiniDTO {

    private Long id;

    private String name;

    private Double price;

    private Long userLimit;

    private Double diskLimit;

    private Long maxProjects;

}
