package com.bgsystem.bugtracker.models.client.bsKB;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
public class bsKBForm {

    private Long id;

    private String title;

    private String content;

    private Long bsKBCategory;

    private Long business;

}
