package com.bgsystem.bugtracker.models.client.project.bsPrComment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class bsPrCommentMiniDTO {

    private Long id;

    private String commentContent;

    private Date commentDate;

}
