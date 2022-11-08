package com.bgsystem.bugtracker.models.client.bsDoc;

import com.bgsystem.bugtracker.models.client.bsDocsCategory.bsDocsCategoryMiniDTO;
import com.bgsystem.bugtracker.models.client.business.BusinessMiniDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsDocListDTO {

    private Long id;

    private String title;

    private String content;

    private bsDocsCategoryMiniDTO bsDocsCategory;

    private BusinessMiniDTO business;

}
