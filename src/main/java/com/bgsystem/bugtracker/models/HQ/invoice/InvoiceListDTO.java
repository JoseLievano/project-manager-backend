package com.bgsystem.bugtracker.models.HQ.invoice;

import com.bgsystem.bugtracker.models.HQ.client.ClientMiniDTO;
import com.bgsystem.bugtracker.models.HQ.plan.PlanMiniDTO;
import com.bgsystem.bugtracker.models.client.business.BusinessMiniDTO;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class InvoiceListDTO {

    private Long id;

    private Double amount;

    private Date dateGenerated;

    private Date limitDate;

    private Boolean isPaid;

    private Boolean overDue;

    private String number;

    private PlanMiniDTO plan;

    private ClientMiniDTO client;

    private BusinessMiniDTO business;

}
