package com.bgsystem.bugtracker.models.client.project.bsPrTask;

import com.bgsystem.bugtracker.models.client.bsInvoice.bsInvoiceMiniDTO;
import com.bgsystem.bugtracker.models.client.bsPriority.bsPriorityMiniDTO;
import com.bgsystem.bugtracker.models.client.bsStatus.bsStatusMiniDTO;
import com.bgsystem.bugtracker.models.client.bsTaskCategory.bsTaskCategoryMiniDTO;
import com.bgsystem.bugtracker.models.client.bsType.bsTypeMiniDTO;
import com.bgsystem.bugtracker.models.client.business.BusinessMiniDTO;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectMiniDTO;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsPrTaskDTO {

    private Long id;

    private String name;

    private String description;

    private Date created;

    private Date dueDate;

    private Boolean isInternal;

    private Boolean isOverDue;

    private Boolean isDone;

    private BusinessMiniDTO business;

    private bsTaskCategoryMiniDTO category;

    private bsProjectMiniDTO project;

    private bsTypeMiniDTO type;

    private bsPriorityMiniDTO priority;

    private bsStatusMiniDTO status;

    private bsInvoiceMiniDTO invoice;

}
