package com.bgsystem.bugtracker.models.client.business;

import com.bgsystem.bugtracker.models.HQ.client.ClientEntity;
import com.bgsystem.bugtracker.models.HQ.invoice.InvoiceEntity;
import com.bgsystem.bugtracker.models.HQ.plan.PlanEntity;
import com.bgsystem.bugtracker.models.client.bsClient.bsClientEntity;
import com.bgsystem.bugtracker.models.client.bsDoc.bsDocEntity;
import com.bgsystem.bugtracker.models.client.bsDocsCategory.bsDocsCategoryEntity;
import com.bgsystem.bugtracker.models.client.bsEmployee.bsEmployeeEntity;
import com.bgsystem.bugtracker.models.client.bsGeneralSettings.bsGeneralSettingsEntity;
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
    private ClientEntity clientEntity;

    @OneToMany(mappedBy = "businessEntity", orphanRemoval = true)
    private Set<InvoiceEntity> invoiceEntities = new LinkedHashSet<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "plan_entity_id", nullable = false)
    private PlanEntity planEntity;

    @OneToMany(mappedBy = "business", orphanRemoval = true)
    private Set<bsClientEntity> bsClientEntities = new LinkedHashSet<>();

    @OneToOne(mappedBy = "business", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private bsGeneralSettingsEntity bsGeneralSettings;

    @OneToMany(mappedBy = "business", orphanRemoval = true)
    private Set<bsManagerEntity> bsManagerEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "business", orphanRemoval = true)
    private Set<bsEmployeeEntity> bsEmployeeEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "business", orphanRemoval = true)
    private Set<bsStatusEntity> bsStatusEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "business", orphanRemoval = true)
    private Set<bsPriorityEntity> bsPriorityEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "business", orphanRemoval = true)
    private Set<bsTypeEntity> bsTypeEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "business", orphanRemoval = true)
    private Set<bsDocsCategoryEntity> bsDocsCategoryEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "business", orphanRemoval = true)
    private Set<bsDocEntity> bsDocEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "business", orphanRemoval = true)
    private Set<bsKBCategoryEntity> bsKBCategoryEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "business", orphanRemoval = true)
    private Set<bsKBEntity> bsKBEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "business", orphanRemoval = true)
    private Set<bsProjectEntity> bsProjectEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "business", orphanRemoval = true)
    private Set<bsTaskCategoryEntity> bsTaskCategoryEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "business", orphanRemoval = true)
    private Set<bsPrTaskEntity> bsPrTaskEntities = new LinkedHashSet<>();

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
