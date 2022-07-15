package com.bgsystem.bugtracker.models.HQ.invoice;

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

    private Long plan;

    private Long client;

    private Long business;

}
