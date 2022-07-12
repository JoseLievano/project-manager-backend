package com.bgsystem.bugtracker.models.HQ.client;

import com.bgsystem.bugtracker.models.HQ.invoice.InvoiceEntity;
import com.bgsystem.bugtracker.models.HQ.mainHQ.MainHQEntity;
import com.bgsystem.bugtracker.models.HQ.plan.PlanEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.shared.models.user.User;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Table (name = "client")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private Boolean isActive;

    @Column
    private Date dateCreated;

    @Column
    private Date lastLoginDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "main_hq_entity_id", nullable = false)
    private MainHQEntity mainHQEntity;

    @OneToMany(mappedBy = "clientEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<BusinessEntity> businessEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "clientEntity", orphanRemoval = true)
    private Set<InvoiceEntity> invoiceEntities = new LinkedHashSet<>();

    //Custom Builder Method for Clients
    @Builder(builderMethodName = "clientBuilder")
    public ClientEntity (Long id,
                         String firstName,
                         String lastName,
                         String email,
                         Set<String> roles,
                         String username,
                         String password,
                         Boolean isActive,
                         Date dateCreated,
                         Date lastLoginDate,
                         MainHQEntity mainHQEntity,
                         Set<BusinessEntity> businessEntities,
                         Set<InvoiceEntity> invoiceEntities){
        super(id, firstName, lastName, email, roles, username, password);
        this.isActive = isActive;
        this.dateCreated = dateCreated;
        this.lastLoginDate = lastLoginDate;
        this.mainHQEntity = mainHQEntity;
        this.businessEntities = businessEntities;
        this.invoiceEntities = invoiceEntities;
    }

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
