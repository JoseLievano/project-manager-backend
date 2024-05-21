package com.bgsystem.bugtracker.models.client.bsKBCategory;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsKBCategoryMiniDTO {

    private Long id;

    private String name;

    private String description;

    private Long level;

    private Boolean isAParentCategory;

}
