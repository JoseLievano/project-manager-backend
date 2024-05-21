package com.bgsystem.bugtracker.models.client.bsKB;

import com.bgsystem.bugtracker.models.client.bsKBCategory.bsKBCategoryMiniDTO;
import com.bgsystem.bugtracker.models.client.business.BusinessMiniDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsKBListDTO {

    private Long id;

    private String title;

    private String content;

    private bsKBCategoryMiniDTO category;

    private BusinessMiniDTO business;

}
