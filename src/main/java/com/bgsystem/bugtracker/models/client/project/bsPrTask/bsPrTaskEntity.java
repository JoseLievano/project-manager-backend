package com.bgsystem.bugtracker.models.client.project.bsPrTask;

import com.bgsystem.bugtracker.models.client.bsInvoice.bsInvoiceEntity;
import com.bgsystem.bugtracker.models.client.bsPriority.bsPriorityEntity;
import com.bgsystem.bugtracker.models.client.bsStatus.bsStatusEntity;
import com.bgsystem.bugtracker.models.client.bsTaskCategory.bsTaskCategoryEntity;
import com.bgsystem.bugtracker.models.client.bsType.bsTypeEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.models.client.project.bsPrChannel.bsPrChannelEntity;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectEntity;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Table(name = "bs_pr_tasks")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsPrTaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Date created;

    @Column
    private Date dueDate;

    @Column
    private Boolean isInternal;

    @Column
    private Boolean isOverDue;

    @Column
    private Boolean isDone;

    @ManyToOne(optional = false)
    @JoinColumn(name = "business_entity_id", nullable = false)
    private BusinessEntity business;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bs_project_id", nullable = false)
    private bsProjectEntity project;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bs_task_category", nullable = false)
    private bsTaskCategoryEntity category;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bs_type_id", nullable = false)
    private bsTypeEntity type;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bs_priority_id", nullable = false)
    private bsPriorityEntity priority;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bs_status_id", nullable = false)
    private bsStatusEntity status;

    @ManyToOne
    @JoinColumn(name = "bs_invoice_id")
    private bsInvoiceEntity invoice;


    @ManyToMany(mappedBy = "tasks", fetch = FetchType.LAZY)
    private Set<bsPrChannelEntity> channels = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        bsPrTaskEntity that = (bsPrTaskEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
