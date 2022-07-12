package com.bgsystem.bugtracker.models.HQ.mainHQ;

import com.bgsystem.bugtracker.models.HQ.client.ClientMiniDTO;
import com.bgsystem.bugtracker.models.HQ.employee.EmployeeMiniDTO;
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
public class MainHQDTO {

    private Long id;

    private String name;

    private Set<PlanMiniDTO> plans;

    private Set<ClientMiniDTO> clients;

    private Set<EmployeeMiniDTO> employees;

    private Set<InvoiceMiniDTO> invoices;

}
