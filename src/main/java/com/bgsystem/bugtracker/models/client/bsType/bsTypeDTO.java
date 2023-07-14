package com.bgsystem.bugtracker.models.client.bsType;

import com.bgsystem.bugtracker.models.client.bsTaskCategory.bsTaskCategoryMiniDTO;
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
public class bsTypeDTO {

    private Long id;

    private String name;

    private BusinessMiniDTO business;

    private Set<bsTaskCategoryMiniDTO> taskCategories;

    private Set<bsPrTaskMiniDTO> tasks;

}
