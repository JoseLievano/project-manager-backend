package com.bgsystem.bugtracker.models.HQ.plan;

import com.bgsystem.bugtracker.models.HQ.invoice.InvoiceEntity;
import com.bgsystem.bugtracker.models.HQ.mainHQ.MainHQEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Table (name = "plan")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private Double price;

    @Column
    private Long userLimit;

    @Column
    private Double diskLimit;

    @Column
    private Long maxProjects;

    @ManyToOne(optional = false)
    @JoinColumn(name = "main_hq_entity_id", nullable = false)
    private MainHQEntity mainHQEntity;

    @OneToMany(mappedBy = "planEntity", orphanRemoval = true)
    private Set<InvoiceEntity> invoiceEntities = new LinkedHashSet<>();

    @Column
    private Long invoiceCount;

    @OneToMany(mappedBy = "plan", orphanRemoval = true)
    private Set<BusinessEntity> businessEntities = new LinkedHashSet<>();

    @Column
    private Long businessCount;

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
