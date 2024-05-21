package com.bgsystem.bugtracker.models.client.bsType;

import com.bgsystem.bugtracker.models.client.bsTaskCategory.bsTaskCategoryMiniDTO;
import com.bgsystem.bugtracker.models.client.business.BusinessMiniDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsTypeListDTO {

    private Long id;

    private String name;

    private BusinessMiniDTO business;

    private Long taskCategories;

    private Long taskCount;

}
