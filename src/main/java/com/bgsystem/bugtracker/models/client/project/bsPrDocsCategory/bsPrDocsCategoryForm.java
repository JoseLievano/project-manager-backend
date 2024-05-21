package com.bgsystem.bugtracker.models.client.project.bsPrDocsCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class bsPrDocsCategoryForm {

    private Long id;

    private String name;

    private Long project;

    private Set<Long> docs;

}
