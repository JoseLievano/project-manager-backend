package com.bgsystem.bugtracker.models.client.project.bsPrDocs;

import com.bgsystem.bugtracker.models.client.project.bsPrDocsCategory.bsPrDocsCategoryEntity;
import com.bgsystem.bugtracker.models.client.project.bsPrDocsCategory.bsPrDocsCategoryMiniDTO;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectMiniDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class bsPrDocsDTO {

    private Long id;

    private String title;

    private String content;

    private bsPrDocsCategoryMiniDTO category;

    private bsProjectMiniDTO project;

}
