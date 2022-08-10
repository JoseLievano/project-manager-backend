package com.bgsystem.bugtracker.models.client.bsInvoice;

import com.bgsystem.bugtracker.models.client.bsClient.bsClientEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.models.client.project.bsPrTask.bsPrTaskEntity;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Table(name = "bs_invoice")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsInvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private Date dateGenerated;

    @Column
    private Date limitDate;

    @Column
    private Double amount;

    @Column
    private Boolean isPaid;

    @Column
    private Boolean isOverDue;

    @Column
    private String number;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bs_client_id", nullable = false)
    private bsClientEntity client;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bs_project_id", nullable = false)
    private bsProjectEntity project;

    @ManyToOne(optional = false)
    @JoinColumn(name = "business_id", nullable = false)
    private BusinessEntity business;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private bsPrTaskEntity task;

}
