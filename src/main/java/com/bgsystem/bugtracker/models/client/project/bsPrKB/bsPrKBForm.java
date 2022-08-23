package com.bgsystem.bugtracker.models.client.project.bsPrKB;

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
public class bsPrKBForm {

    private Long id;

    private String title;

    private String content;

    private Long project;

    private Long category;

}
