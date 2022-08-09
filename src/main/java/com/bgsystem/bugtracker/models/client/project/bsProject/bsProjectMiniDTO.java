package com.bgsystem.bugtracker.models.client.project.bsProject;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsProjectMiniDTO {

    private Long id;

    private String name;

    private Boolean isCompleted;

    private Date created;

    private Date dueDate;

}
