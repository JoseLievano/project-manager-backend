package com.bgsystem.bugtracker.models.client.project.bsPrKB;

import com.bgsystem.bugtracker.models.client.project.bsPrKBCategory.bsPrKBCategoryMiniDTO;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectMiniDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class bsPrKBDTO {

    private Long id;

    private String title;

    private String content;

    private bsProjectMiniDTO project;

    private bsPrKBCategoryMiniDTO category;

}
