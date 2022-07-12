package com.bgsystem.bugtracker.models.client.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class BusinessForm {

    private Long id;

    private String name;

    private String taxID;

    private Long client;

    private Set<Long> invoices;

    private Long plan;

}
