package com.bgsystem.bugtracker.models.client.bsInvoice;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsInvoiceMiniDTO {

    private Long id;

    private Date dateGenerated;

    private Date limitDate;

    private Double amount;

    private Boolean isPaid;

    private Boolean isOverDue;

    private String number;

}
