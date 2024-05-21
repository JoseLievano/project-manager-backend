package com.bgsystem.bugtracker.models.client.bsDocsCategory;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsDocsCategoryMiniDTO {

    private Long id;

    private String name;

    private String description;

    private Boolean isAParentCategory;

    private Long level;
}
