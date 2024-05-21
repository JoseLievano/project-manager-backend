package com.bgsystem.bugtracker.models.HQ.invoice;

import com.bgsystem.bugtracker.models.HQ.mainHQ.MainHQMiniDTO;
import com.bgsystem.bugtracker.models.HQ.plan.PlanMiniDTO;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class InvoiceMiniDTO {

    private Long id;

    private Double amount;

    private Date dateGenerated;

    private Date limitDate;

    private Boolean isPaid;

    private Boolean overDue;

    private String number;

}
