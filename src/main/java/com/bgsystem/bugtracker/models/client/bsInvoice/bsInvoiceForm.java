package com.bgsystem.bugtracker.models.client.bsInvoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class bsInvoiceForm {

    private Long id;

    private Date dateGenerated;

    private Date limitDate;

    private Double amount;

    private Boolean isPaid;

    private Boolean isOverDue;

    private String number;

    private Long client;

    private Long project;

    private Long business;

    private Long task;

}
