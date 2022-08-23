package com.bgsystem.bugtracker.models.client.project.bsPrDocs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class bsPrDocsForm {

    private Long id;

    private String title;

    private String content;

    private Long category;

    private Long project;

}
