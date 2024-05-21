package com.bgsystem.bugtracker.models.client.bsDoc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
public class bsDocForm {

    private Long id;

    private String title;

    private String content;

    private Long category;

    private Long business;

}
