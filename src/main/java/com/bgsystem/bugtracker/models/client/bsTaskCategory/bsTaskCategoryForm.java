package com.bgsystem.bugtracker.models.client.bsTaskCategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
public class bsTaskCategoryForm {

    private Long id;

    private String name;

    private Long business;

    private Set<Long> tasks;

}
