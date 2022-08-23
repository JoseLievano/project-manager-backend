package com.bgsystem.bugtracker.models.client.project.bsPrKBCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Validated
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class bsPrKBCategoryForm {

    private Long id;

    private String name;

    private Long project;

}
