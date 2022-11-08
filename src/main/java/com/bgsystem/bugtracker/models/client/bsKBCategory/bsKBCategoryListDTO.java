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

    private BusinessMiniDTO business;

    private Long bsKBCount;

}
