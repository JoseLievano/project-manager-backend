package com.bgsystem.bugtracker.models.client.business;

import com.bgsystem.bugtracker.models.HQ.client.ClientMiniDTO;
import com.bgsystem.bugtracker.models.HQ.plan.PlanMiniDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BusinessListDTO {

    private Long id;

    private String name;

    private String taxID;

    private ClientMiniDTO client;

    private PlanMiniDTO plan;

    private Long invoices;

    private Long bsClients;

    private Long bsManagers;

    private Long bsEmployees;

    private Long bsStatuses;

    private Long bsPriorities;

    private Long bsTypes;

    private Long bsDocsCategories;

    private Long bsDocs;

    private Long bsKBCategories;

    private Long bsKBs;

    private Long bsProjects;

    private Long bsTaskCategories;

    private Long bsPrTasks;

    private Long bsInvoices;

}
