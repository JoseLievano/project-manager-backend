package com.bgsystem.bugtracker.models.client.project.bsPrTask;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsPrTaskMiniDTO {

    private Long id;

    private String name;

    private String description;

    private Date created;

    private Date dueDate;

    private Boolean isInternal;

    private Boolean isOverDue;

    private Boolean isDone;

}
