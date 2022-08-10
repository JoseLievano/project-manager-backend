package com.bgsystem.bugtracker.models.client.business;

import com.bgsystem.bugtracker.models.HQ.client.ClientMiniDTO;
import com.bgsystem.bugtracker.models.HQ.invoice.InvoiceMiniDTO;
import com.bgsystem.bugtracker.models.HQ.plan.PlanMiniDTO;
import com.bgsystem.bugtracker.models.client.bsClient.*;
import com.bgsystem.bugtracker.models.client.bsDoc.bsDocMiniDTO;
import com.bgsystem.bugtracker.models.client.bsDocsCategory.bsDocsCategoryMiniDTO;
import com.bgsystem.bugtracker.models.client.bsEmployee.bsEmployeeMiniDTO;
import com.bgsystem.bugtracker.models.client.bsGeneralSettings.bsGeneralSettingsMiniDTO;
import com.bgsystem.bugtracker.models.client.bsInvoice.bsInvoiceMiniDTO;
import com.bgsystem.bugtracker.models.client.bsManager.bsManagerMiniDTO;
import com.bgsystem.bugtracker.models.client.bsPriority.bsPriorityMiniDTO;
import com.bgsystem.bugtracker.models.client.bsStatus.bsStatusMiniDTO;
import com.bgsystem.bugtracker.models.client.bsTaskCategory.bsTaskCategoryMiniDTO;
import com.bgsystem.bugtracker.models.client.bsType.bsTypeMiniDTO;
import com.bgsystem.bugtracker.models.client.project.bsPrTask.bsPrTaskMiniDTO;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectMiniDTO;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BusinessDTO {

    private Long id;

    private String name;

    private String taxID;

    private ClientMiniDTO client;

    private bsGeneralSettingsMiniDTO bsGeneralSettings;

    private Set<InvoiceMiniDTO> invoices;

    private PlanMiniDTO plan;

    private Set<bsClientMiniDTO> bsClients;

    private Set<bsManagerMiniDTO> bsManagers;

    private Set<bsEmployeeMiniDTO> bsEmployees;

    private Set<bsStatusMiniDTO> bsStatuses;

    private Set<bsPriorityMiniDTO> bsPriorities;

    private Set<bsTypeMiniDTO> bsTypes;

    private Set<bsDocsCategoryMiniDTO> bsDocsCategories;

    private Set<bsDocMiniDTO> bsDocs;

    private Set<bsProjectMiniDTO> bsProjects;

    private Set<bsTaskCategoryMiniDTO> bsTaskCategories;

    private Set<bsPrTaskMiniDTO> bsPrTasks;

    private Set<bsInvoiceMiniDTO> bsInvoices;

}
