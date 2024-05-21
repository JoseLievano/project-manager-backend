package com.bgsystem.bugtracker.models.client.bsStatus;

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
public class bsStatusDTO {

    private Long id;

    private String name;

    private String color;

    private BusinessMiniDTO business;

    private Set<bsPrTaskMiniDTO> tasks;

}
