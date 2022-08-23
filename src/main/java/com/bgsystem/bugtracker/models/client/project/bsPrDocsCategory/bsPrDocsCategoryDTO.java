package com.bgsystem.bugtracker.models.client.project.bsPrDocsCategory;

import com.bgsystem.bugtracker.models.client.project.bsPrDocs.bsPrDocsMiniDTO;
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
public class bsPrDocsCategoryDTO {

    private Long id;

    private String name;

    private bsProjectMiniDTO project;

    private Set<bsPrDocsMiniDTO> docs;

}
