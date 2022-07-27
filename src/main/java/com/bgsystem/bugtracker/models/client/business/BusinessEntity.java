package com.bgsystem.bugtracker.models.client.business;

import com.bgsystem.bugtracker.models.HQ.client.ClientEntity;
import com.bgsystem.bugtracker.models.HQ.invoice.InvoiceEntity;
import com.bgsystem.bugtracker.models.HQ.plan.PlanEntity;
import com.bgsystem.bugtracker.models.client.bsClient.bsClientEntity;
import com.bgsystem.bugtracker.models.client.bsGeneralSettings.bsGeneralSettingsEntity;
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
