package com.bgsystem.bugtracker.models.client.bsDocsCategory;

import com.bgsystem.bugtracker.models.client.business.BusinessMiniDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class bsDocsCategoryListDTO {

    private Long id;

    private String name;

    private BusinessMiniDTO business;

    private Long bsDocsCount;

}
