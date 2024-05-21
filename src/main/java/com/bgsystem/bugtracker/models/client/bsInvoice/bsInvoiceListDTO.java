package com.bgsystem.bugtracker.models.client.bsInvoice;

import com.bgsystem.bugtracker.models.client.bsClient.bsClientMiniDTO;
import com.bgsystem.bugtracker.models.client.business.BusinessMiniDTO;
import com.bgsystem.bugtracker.models.client.project.bsPrTask.bsPrTaskMiniDTO;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectMiniDTO;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsInvoiceListDTO {

    private Long id;

    private Date dateGenerated;

    private Date limitDate;

    private Double amount;

    private Boolean isPaid;

    private Boolean isOverDue;

    private String number;

    private bsClientMiniDTO client;

    private bsProjectMiniDTO project;

    private BusinessMiniDTO business;

    private bsPrTaskMiniDTO task;

}
