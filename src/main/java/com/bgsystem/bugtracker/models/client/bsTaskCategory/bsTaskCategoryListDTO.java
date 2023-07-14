package com.bgsystem.bugtracker.models.client.bsTaskCategory;

import com.bgsystem.bugtracker.models.client.business.BusinessMiniDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsTaskCategoryListDTO {

    private Long id;

    private String name;

    private BusinessMiniDTO business;

    private Long types;

    private Long tasks;

}
