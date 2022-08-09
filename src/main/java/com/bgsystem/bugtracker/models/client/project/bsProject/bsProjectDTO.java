package com.bgsystem.bugtracker.models.client.project.bsProject;

import com.bgsystem.bugtracker.models.client.bsClient.bsClientMiniDTO;
import com.bgsystem.bugtracker.models.client.business.BusinessMiniDTO;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsProjectDTO {

    private Long id;

    private String name;

    private Boolean isCompleted;

    private Date created;

    private Date dueDate;

    private BusinessMiniDTO business;

    private bsClientMiniDTO client;

}
