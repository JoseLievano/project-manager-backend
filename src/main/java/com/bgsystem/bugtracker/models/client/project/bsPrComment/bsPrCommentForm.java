package com.bgsystem.bugtracker.models.client.project.bsPrComment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
public class bsPrCommentForm {

    private Long id;

    private String commentContent;

    private Date commentDate;

    private Long author;

    private Long channel;

    private Long project;

}
