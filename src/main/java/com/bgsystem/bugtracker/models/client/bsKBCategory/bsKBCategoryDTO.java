package com.bgsystem.bugtracker.models.client.bsKBCategory;

import com.bgsystem.bugtracker.models.client.bsKB.bsKBMiniDTO;
import com.bgsystem.bugtracker.models.client.business.BusinessMiniDTO;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsKBCategoryDTO {

    private Long id;

    private String name;

    private String description;

    private Long level;

    private Boolean isAParentKBCategory;

    private bsKBCategoryMiniDTO parentCategory;

    private Set<bsKBCategoryMiniDTO> subCategories;

    private BusinessMiniDTO business;

    private Set<bsKBMiniDTO> bsKBs;

}
