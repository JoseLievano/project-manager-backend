package com.bgsystem.bugtracker.models.client.bsDocsCategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
public class bsDocsCategoryForm {

    private Long id;

    private String name;

    private String description;

    private Long parentCategory;

    private Long business;

}
