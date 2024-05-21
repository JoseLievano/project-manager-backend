package com.bgsystem.bugtracker.models.client.project.bsProject;

import com.bgsystem.bugtracker.models.client.bsClient.bsClientEntity;
import com.bgsystem.bugtracker.models.client.bsInvoice.bsInvoiceEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.models.client.project.bsPrChannel.bsPrChannelEntity;
import com.bgsystem.bugtracker.models.client.project.bsPrDocs.bsPrDocsEntity;
import com.bgsystem.bugtracker.models.client.project.bsPrDocsCategory.bsPrDocsCategoryEntity;
import com.bgsystem.bugtracker.models.client.project.bsPrKB.bsPrKBEntity;
import com.bgsystem.bugtracker.models.client.project.bsPrKBCategory.bsPrKBCategoryEntity;
import com.bgsystem.bugtracker.models.client.project.bsPrTask.bsPrTaskEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table(name = "bs_projects")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @Column
    private Boolean isCompleted;

    @Column
    private Date created;

    @Column
    private Date dueDate;

    //Todo: Add an Owner field to the project entity

    @ManyToOne(optional = false)
    @JoinColumn(name = "business_entity_id", nullable = false)
    private BusinessEntity business;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bs_client_id", nullable = false)
    private bsClientEntity client;

    @OneToMany(mappedBy = "project")
    private Set<bsPrTaskEntity> tasks;

    @Column
    private Long taskCount;

    @OneToMany(mappedBy = "project")
    private Set<bsInvoiceEntity> invoices;

    @Column
    private Long invoiceCount;

    @OneToMany(mappedBy = "project")
    private Set<bsPrChannelEntity> channels;

    @Column
    private Long channelCount;

    @OneToMany(mappedBy = "project")
    private Set<bsPrDocsCategoryEntity> docsCategories;

    @Column
    private Long docsCategoryCount;

    @OneToMany(mappedBy = "project")
    private Set<bsPrDocsEntity> docs;

    @Column
    private Long docsCount;

    @OneToMany(mappedBy = "project")
    private Set<bsPrKBCategoryEntity> kbCategories;

    @Column
    private Long kbCategoryCount;

    @OneToMany(mappedBy = "project")
    private Set<bsPrKBEntity> kbs;

    @Column
    private Long kbCount;

}
