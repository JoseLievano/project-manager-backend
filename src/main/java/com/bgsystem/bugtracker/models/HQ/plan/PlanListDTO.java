package com.bgsystem.bugtracker.models.HQ.plan;

import com.bgsystem.bugtracker.models.HQ.invoice.InvoiceMiniDTO;
import com.bgsystem.bugtracker.models.client.business.BusinessMiniDTO;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PlanListDTO {

    private Long id;

    private String name;

    private Double price;

    private Long userLimit;

    private Double diskLimit;

    private Long maxProjects;

    private Long invoiceCount;

    private Long businessCount;

}
