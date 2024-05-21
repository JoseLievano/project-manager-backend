package com.bgsystem.bugtracker.models.client.bsPriority;

import com.bgsystem.bugtracker.models.client.business.BusinessMiniDTO;
import com.bgsystem.bugtracker.models.client.project.bsPrTask.bsPrTaskMiniDTO;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsPriorityDTO {

    private Long id;

    private String name;

    private Integer priorityOrder;

    private BusinessMiniDTO business;

    private Set<bsPrTaskMiniDTO> tasks;

}
