package com.bgsystem.bugtracker.models.client.business;

import com.bgsystem.bugtracker.models.HQ.client.ClientEntity;
import com.bgsystem.bugtracker.models.HQ.invoice.InvoiceEntity;
import com.bgsystem.bugtracker.models.HQ.plan.PlanEntity;
import com.bgsystem.bugtracker.models.client.bsClient.bsClientEntity;
import com.bgsystem.bugtracker.models.client.bsDoc.bsDocEntity;
import com.bgsystem.bugtracker.models.client.bsDocsCategory.bsDocsCategoryEntity;
import com.bgsystem.bugtracker.models.client.bsEmployee.bsEmployeeEntity;
import com.bgsystem.bugtracker.models.client.bsGeneralSettings.bsGeneralSettingsEntity;
import com.bgsystem.bugtracker.models.client.bsInvoice.bsInvoiceEntity;
import com.bgsystem.bugtracker.models.client.bsKB.bsKBEntity;
import com.bgsystem.bugtracker.models.client.bsKBCategory.bsKBCategoryEntity;
import com.bgsystem.bugtracker.models.client.bsManager.bsManagerEntity;
import com.bgsystem.bugtracker.models.client.bsPriority.bsPriorityEntity;
import com.bgsystem.bugtracker.models.client.bsStatus.bsStatusEntity;
import com.bgsystem.bugtracker.models.client.bsTaskCategory.bsTaskCategoryEntity;
import com.bgsystem.bugtracker.models.client.bsType.bsTypeEntity;
import com.bgsystem.bugtracker.models.client.project.bsPrTask.bsPrTaskEntity;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectEntity;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Table (name = "business")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BusinessEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column (unique = true, length = 100)
    private String name;

    @Column
    private String taxID;

    @Column
    private Date dateCreated;

    @Column
    private Boolean pendingInvoice;

    @Column
    private Boolean overDue;

    @Column
    private Boolean isActive;

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_entity_id", nullable = false)
    private ClientEntity client;

    @ManyToOne(optional = false)
    @JoinColumn(name = "plan_entity_id", nullable = false)
    private PlanEntity plan;

    @OneToOne(mappedBy = "business", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private bsGeneralSettingsEntity bsGeneralSettings;

    @OneToMany(mappedBy = "businessEntity", orphanRemoval = true)
    private Set<InvoiceEntity> invoices = new LinkedHashSet<>();

    @Column
    private Long invoiceCount;

    @OneToMany(mappedBy = "business", orphanRemoval = true)
    private Set<bsClientEntity> bsClients = new LinkedHashSet<>();

    @Column
    private Long bsClientCount;

    @OneToMany(mappedBy = "business", orphanRemoval = true)
    private Set<bsManagerEntity> bsManagers = new LinkedHashSet<>();

    @Column
    private Long bsManagerCount;

    @OneToMany(mappedBy = "business", orphanRemoval = true)
    private Set<bsEmployeeEntity> bsEmployees = new LinkedHashSet<>();

    @Column
    private Long bsEmployeeCount;

    @OneToMany(mappedBy = "business", orphanRemoval = true)
    private Set<bsStatusEntity> bsStatuses = new LinkedHashSet<>();

    @Column
    private Long bsStatusCount;

    @OneToMany(mappedBy = "business", orphanRemoval = true)
    private Set<bsPriorityEntity> bsPriorities = new LinkedHashSet<>();

    @Column
    private Long bsPriorityCount;

    @OneToMany(mappedBy = "business", orphanRemoval = true)
    private Set<bsTypeEntity> bsTypes = new LinkedHashSet<>();

    @Column
    private Long bsTypeCount;

    @OneToMany(mappedBy = "business", orphanRemoval = true)
    private Set<bsDocsCategoryEntity> bsDocsCategories = new LinkedHashSet<>();

    @Column
    private Long bsDocsCategoryCount;

    @OneToMany(mappedBy = "business", orphanRemoval = true)
    private Set<bsDocEntity> bsDocs = new LinkedHashSet<>();

    @Column
    private Long bsDocCount;

    @OneToMany(mappedBy = "business", orphanRemoval = true)
    private Set<bsKBCategoryEntity> bsKBCategories = new LinkedHashSet<>();

    @Column
    private Long bsKBCategoryCount;

    @OneToMany(mappedBy = "business", orphanRemoval = true)
    private Set<bsKBEntity> bsKBs = new LinkedHashSet<>();

    @Column
    private Long bsKBCount;

    @OneToMany(mappedBy = "business", orphanRemoval = true)
    private Set<bsProjectEntity> bsProjects = new LinkedHashSet<>();

    @Column
    private Long bsProjectCount;

    @OneToMany(mappedBy = "business", orphanRemoval = true)
    private Set<bsTaskCategoryEntity> bsTaskCategories = new LinkedHashSet<>();

    @Column
    private Long bsTaskCategoryCount;

    @OneToMany(mappedBy = "business", orphanRemoval = true)
    private Set<bsPrTaskEntity> bsPrTasks = new LinkedHashSet<>();

    @Column
    private Long bsPrTaskCount;

    @OneToMany(mappedBy = "business", orphanRemoval = true)
    private Set<bsInvoiceEntity> bsInvoices = new LinkedHashSet<>();

    @Column
    private Long bsInvoiceCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PlanEntity that = (PlanEntity) o;
        return id != null && Objects.equals(id, that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
