package com.bgsystem.bugtracker.models.client.business;

import com.bgsystem.bugtracker.models.HQ.client.ClientMiniDTO;
import com.bgsystem.bugtracker.models.HQ.invoice.InvoiceMiniDTO;
import com.bgsystem.bugtracker.models.HQ.plan.PlanMiniDTO;
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

    private Set<InvoiceMiniDTO> invoices;

    private PlanMiniDTO plan;

}
