package com.bgsystem.bugtracker.models.client.business;

import com.bgsystem.bugtracker.models.HQ.client.ClientMiniDTO;
import com.bgsystem.bugtracker.models.HQ.invoice.InvoiceMiniDTO;
import com.bgsystem.bugtracker.models.HQ.plan.PlanMiniDTO;
import com.bgsystem.bugtracker.models.client.bsClient.*;
import com.bgsystem.bugtracker.models.client.bsGeneralSettings.bsGeneralSettingsMiniDTO;
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

}
