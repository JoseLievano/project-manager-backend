package com.bgsystem.bugtracker.models.HQ.invoice;

import com.bgsystem.bugtracker.models.HQ.client.ClientEntity;
import com.bgsystem.bugtracker.models.HQ.mainHQ.MainHQEntity;
import com.bgsystem.bugtracker.models.HQ.plan.PlanEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Table (name = "invoice")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private Double amount;

    @Column
    @DateTimeFormat(pattern="yyyy.MM.dd HH:mm:ss")
    private Date dateGenerated;

    @Column
    @DateTimeFormat(pattern="yyyy.MM.dd HH:mm:ss")
    private Date limitDate;

    @Column
    private Boolean isPaid;

    @Column
    private Boolean overDue;

    @Column
    private String number;

    @ManyToOne(optional = false)
    @JoinColumn(name = "main_hq_entity_id", nullable = false)
    private MainHQEntity mainHQEntity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "plan_entity_id", nullable = false)
    private PlanEntity planEntity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_entity_id", nullable = false)
    private ClientEntity clientEntity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "business_entity_id", nullable = false)
    private BusinessEntity businessEntity;

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
