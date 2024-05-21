package com.bgsystem.bugtracker.models.client.business;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BusinessMiniDTO {

    private Long id;

    private String name;

    private String taxID;

    private Date dateCreated;

    private Boolean pendingInvoice;

    private Boolean overDue;

    private Boolean isActive;

}
