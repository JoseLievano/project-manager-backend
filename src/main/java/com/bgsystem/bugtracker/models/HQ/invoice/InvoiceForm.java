package com.bgsystem.bugtracker.models.HQ.invoice;

import com.bgsystem.bugtracker.models.HQ.client.ClientMiniDTO;
import com.bgsystem.bugtracker.models.HQ.mainHQ.MainHQMiniDTO;
import com.bgsystem.bugtracker.models.HQ.plan.PlanMiniDTO;
import com.bgsystem.bugtracker.models.client.business.BusinessMiniDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.util.Date;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceForm {

    private Long id;

    private Double amount;

    private Date dateGenerated;

    @Future
    private Date limitDate;

    private Boolean isPaid;

    @NotBlank
    private String number;

    @NotEmpty
    private Long plan;

    @NotEmpty
    private Long client;

    @NotEmpty
    private Long business;

}
