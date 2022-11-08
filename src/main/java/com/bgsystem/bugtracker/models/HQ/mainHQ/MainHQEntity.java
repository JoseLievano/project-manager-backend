package com.bgsystem.bugtracker.models.HQ.mainHQ;

import com.bgsystem.bugtracker.models.HQ.client.ClientEntity;
import com.bgsystem.bugtracker.models.HQ.employee.EmployeeEntity;
import com.bgsystem.bugtracker.models.HQ.invoice.InvoiceEntity;
import com.bgsystem.bugtracker.models.HQ.plan.PlanEntity;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Table (name = "mainHQ")
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MainHQEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column( length = 250)
    private String name;

    @OneToMany(mappedBy = "mainHQEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<PlanEntity> planEntities = new LinkedHashSet<>();

    @Column
    private Long planCount;

    @OneToMany(mappedBy = "mainHQEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<InvoiceEntity> invoiceEntities = new LinkedHashSet<>();

    @Column
    private Long invoiceCount;

    @OneToMany(mappedBy = "mainHQEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<ClientEntity> clientEntities = new LinkedHashSet<>();

    @Column
    private Long clientCount;

    @OneToMany(mappedBy = "mainHQEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<EmployeeEntity> employeeEntities = new LinkedHashSet<>();

    @Column
    private Long employeeCount;

}
