package com.bgsystem.bugtracker.models.client.bsKBCategory;

import com.bgsystem.bugtracker.models.client.business.BusinessMiniDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsKBCategoryListDTO {

    private Long id;

    private String name;

    private String description;

    private Long level;

    private Boolean isAParentCategory;

    private bsKBCategoryMiniDTO parentCategory;

    private Long subCategories;

    private BusinessMiniDTO business;

    private Long bsKBs;

}
