package com.bgsystem.bugtracker.models.client.project.bsProject;

import com.bgsystem.bugtracker.models.client.bsClient.bsClientMiniDTO;
import com.bgsystem.bugtracker.models.client.business.BusinessMiniDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class bsProjectListDTO {

    private Long id;

    private String name;

    private Boolean isCompleted;

    private Date created;

    private Date dueDate;

    private BusinessMiniDTO business;

    private bsClientMiniDTO client;

    private Long taskCount;

    private Long invoiceCount;

    private Long channelCount;

    private Long docsCategoryCount;

    private Long docsCount;

    private Long kbCategoryCount;

    private Long kbCount;

}
