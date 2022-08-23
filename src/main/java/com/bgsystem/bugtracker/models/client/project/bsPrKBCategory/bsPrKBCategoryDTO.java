package com.bgsystem.bugtracker.models.client.project.bsPrKBCategory;

import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectMiniDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class bsPrKBCategoryDTO {

    private Long id;

    private String name;

    private bsProjectMiniDTO project;

}
