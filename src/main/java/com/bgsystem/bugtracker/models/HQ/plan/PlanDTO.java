package com.bgsystem.bugtracker.models.HQ.plan;

import com.bgsystem.bugtracker.models.HQ.invoice.InvoiceMiniDTO;
import com.bgsystem.bugtracker.models.HQ.mainHQ.MainHQMiniDTO;
import com.bgsystem.bugtracker.models.client.business.BusinessMiniDTO;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PlanDTO {

    private Long id;

    private String name;

    private Double price;

    private Long userLimit;

    private Double diskLimit;

    private Long maxProjects;

    private Set<InvoiceMiniDTO> invoices;

    private Set<BusinessMiniDTO> businesses;

}
