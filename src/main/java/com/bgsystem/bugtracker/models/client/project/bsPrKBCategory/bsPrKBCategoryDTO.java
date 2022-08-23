package com.bgsystem.bugtracker.models.client.project.bsPrKBCategory;

import com.bgsystem.bugtracker.models.client.project.bsPrKB.bsPrKBEntity;
import com.bgsystem.bugtracker.models.client.project.bsPrKB.bsPrKBMiniDTO;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectMiniDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class bsPrKBCategoryDTO {

    private Long id;

    private String name;

    private bsProjectMiniDTO project;

    private Set<bsPrKBMiniDTO> kbs;

}
