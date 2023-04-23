package com.bgsystem.bugtracker.models.client.bsKBCategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
public class bsKBCategoryForm {

    private Long id;

    private String name;

    private String description;

    private Long parentKB;

    private Long business;
}
