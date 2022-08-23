package com.bgsystem.bugtracker.models.client.project.bsPrKB;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class bsPrKBMiniDTO {

    private Long id;

    private String title;

    private String content;

}
