package com.bgsystem.bugtracker.models.client.bsDocsCategory;

import com.bgsystem.bugtracker.models.client.bsDoc.bsDocMiniDTO;
import com.bgsystem.bugtracker.models.client.business.BusinessMiniDTO;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class bsDocsCategoryDTO {

    private Long id;

    private String name;

    private String description;

    private Boolean isAParentCategory;

    private Long level;

    private bsDocsCategoryMiniDTO parentCategory;

    private BusinessMiniDTO business;

    private Set<bsDocsCategoryMiniDTO> subCategories;

    private Set<bsDocMiniDTO> bsDocs;

}
