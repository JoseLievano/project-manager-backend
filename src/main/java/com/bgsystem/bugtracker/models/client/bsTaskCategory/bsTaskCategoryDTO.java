package com.bgsystem.bugtracker.models.client.bsTaskCategory;

import com.bgsystem.bugtracker.models.client.bsType.bsTypeMiniDTO;
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
public class bsTaskCategoryDTO {

    private Long id;

    private String name;

    private BusinessMiniDTO business;

    private Set<bsTypeMiniDTO> types;

    private Set<bsPrTaskMiniDTO> tasks;

}
